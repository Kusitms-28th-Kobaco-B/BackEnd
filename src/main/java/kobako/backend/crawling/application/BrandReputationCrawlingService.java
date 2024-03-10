package kobako.backend.crawling.application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import kobako.backend.crawling.domain.BrandReputation;
import kobako.backend.crawling.domain.BrandReputationCategory;
import kobako.backend.crawling.presentation.dto.response.BrandReputationCrawlingResponse;
import kobako.backend.naver.presentation.dto.request.CharacterAnalysisRequest;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class BrandReputationCrawlingService {

    private final String baseUrl = "https://brikorea.com/rk";
    private final String datePrefix = "yyMM";
    private final int oneMonth = 1;
    private final BrandReputationCategory[] categories = {
        BrandReputationCategory.of("/ad", "광고 모델 부문"),
        BrandReputationCategory.of("/enter", "예능인 부문"),
        BrandReputationCategory.of("/drama", "드라마 배우 부문"),
        BrandReputationCategory.of("/actor", "배우 부문"),
        BrandReputationCategory.of("/star", "스타 부문"),
        BrandReputationCategory.of("/boy", "보이그룹 부문"),
        BrandReputationCategory.of("/girl", "걸그룹 부문"),
        BrandReputationCategory.of("/trot", "트로트 가수 부문"),
        BrandReputationCategory.of("/movie", "영화 배우 부문"),
        BrandReputationCategory.of("/sports", "스포츠 스타 부문")};
    private String thisYearMonth;

    public BrandReputationCrawlingService() {
        LocalDate now = LocalDate.now().minusMonths(oneMonth);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePrefix);
        this.thisYearMonth = now.format(formatter);
    }

    public BrandReputationCrawlingResponse getHighestBrandReputation(
        CharacterAnalysisRequest request) {
        int highestRank = Integer.MAX_VALUE;
        List<BrandReputation> highestReputations = new ArrayList<>();
        String highestCategory = "";
        for (BrandReputationCategory category : categories) {
            List<BrandReputation> brandReputations = getBrandReputationRank(
	baseUrl + category.getUrl(),
	category.getCategory(), request);
            if (brandReputations != null) {
	for (BrandReputation brandReputation : brandReputations) {
	    if (brandReputation.getRank() < highestRank) {
	        highestRank = brandReputation.getRank();
	        highestCategory = category.getCategory();
	        highestReputations = brandReputations;
	    }
	}
            }
        }
        if (highestRank == Integer.MAX_VALUE) {
            return null;
        }
        return new BrandReputationCrawlingResponse(highestCategory, highestRank,
            List.of(highestReputations.get(0), highestReputations.get(1),
	highestReputations.get(2)));
    }


    public List<BrandReputation> getBrandReputationRank(final String categoryUrl,
        final String category,
        final CharacterAnalysisRequest request) {
        List<BrandReputation> brandReputations = new ArrayList<>();
        boolean isContain = false;
        try {
            Connection connection = Jsoup.connect(baseUrl + categoryUrl + thisYearMonth);
            Document document = connection.get();
            Elements tableRows = document.select(
	".table.table-bordered.table-striped.bri_rank_table tbody tr");

            int count = 0;
            for (Element row : tableRows) {
	Elements columns = row.select("td");
	if (count > 99) {
	    break;
	}
	if (columns.size() > 1) {
	    int rank = Integer.parseInt(columns.get(0).text().replace(",", ""));
	    String name = columns.get(1).text();
	    brandReputations.add(BrandReputation.of(rank, name, category));

	    if (name.equals(request.keyword())) {
	        isContain = true;
	    }
	}
	count++;
            }

            return isContain ? brandReputations : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
