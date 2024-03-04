package kobako.backend.crawling.application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import kobako.backend.crawling.domain.BrandReputation;
import kobako.backend.crawling.domain.BrandReputationCategory;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingRequest;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingResponse;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class BrandReputationCrawlingService {

    private final String baseUrl = "https://brikorea.com/rk/";
    private final BrandReputationCategory[] categories = {
        BrandReputationCategory.of("ad", "광고 모델 부문"),
        BrandReputationCategory.of("enter", "예능인 부문"),
        BrandReputationCategory.of("drama", "드라마 배우 부문")};
    private String thisYearMonth;

    public BrandReputationCrawlingService() {
        LocalDate now = LocalDate.now().minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMM");
        this.thisYearMonth = now.format(formatter);
    }

    public BrandReputationCrawlingResponse getHighestBrandReputation(
        BrandReputationCrawlingRequest request) {
        int highestRank = Integer.MAX_VALUE;
        String highestCategory = "";
        for (BrandReputationCategory category : categories) {
            BrandReputation brandReputation = getBrandReputationRank(baseUrl + category.getUrl(),
	category.getCategory(), request);
            if (brandReputation != null && brandReputation.getRank() < highestRank) {
	highestRank = brandReputation.getRank();
	highestCategory = category.getCategory();
            }
        }
        if (highestRank == Integer.MAX_VALUE) {
            return null;
        }
        return new BrandReputationCrawlingResponse(highestCategory, highestRank, request.keyword());
    }


    public BrandReputation getBrandReputationRank(final String baseUrl, final String category,
        final BrandReputationCrawlingRequest request) {
        try {
            Connection connection = Jsoup.connect(baseUrl+thisYearMonth);
            Document document = connection.get();
            Elements tableRows = document.select(".table.table-bordered.bri_rank_table tbody tr");

            List<BrandReputation> rankings = new ArrayList<>();
            for (Element row : tableRows) {
	Elements columns = row.select("td");
	if (rankings.size() > 99) {
	    break;
	}
	if (columns.size() > 1) {
	    int rank = Integer.parseInt(columns.get(0).text().replace(",", ""));
	    String name = columns.get(1).text();

	    if (name.equals(request.keyword())) {
	        return new BrandReputation(rank, category);
	    }
	}
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to crawl brand reputation");
        }
    }

}
