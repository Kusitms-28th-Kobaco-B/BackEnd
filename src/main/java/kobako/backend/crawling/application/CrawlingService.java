package kobako.backend.crawling.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kobako.backend.crawling.domain.BrandReputation;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingRequest;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CrawlingService {

    public List<BrandReputation> crawlBrandReputation(BrandReputationCrawlingRequest request) {
        final String brandReputationUrl =
            "https://brikorea.com/rk/star" + request.year() + request.month();

        try {
            Connection connection = Jsoup.connect(brandReputationUrl);
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

	    rankings.add(new BrandReputation(rank, name));
	}
            }

            return rankings;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to crawl brand reputation");
        }
    }
}
