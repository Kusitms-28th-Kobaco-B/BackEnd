package kobako.backend.crawling.application;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import kobako.backend.crawling.domain.BrandReputation;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingRequest;
import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingRequest;
import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CrawlingService {

    private final String brandReputationBaseUri = "https://brikorea.com/rk/star";
    private final String kakaoDataTrendBaseUri = "https://datatrend.kakao.com/result";

    public List<BrandReputation> crawlBrandReputation(BrandReputationCrawlingRequest request) {
        final String uri = brandReputationBaseUri + request.year() + request.month();

        try {
            Connection connection = Jsoup.connect(uri);
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

    public KakaoDataTrendCrawlingResponse crawlKakaoDataTrend(
        KakaoDataTrendCrawlingRequest request) {
        WebDriver webDriver = null;
        try {
            String encodedKeyword = URLEncoder.encode(request.keyword(), "UTF-8");
            final String url = kakaoDataTrendBaseUri + "?q=" + encodedKeyword + "&from="
	+ request.startDate() + "&to=" + request.endDate();

            System.setProperty("webdriver.chrome.driver", "/Users/gundorit/chromedriver");
            webDriver = new ChromeDriver();
            webDriver.get(url);

            // WebDriverWait를 사용하여 동적 콘텐츠가 로드될 때까지 기다립니다.
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(1000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
	By.cssSelector("div.wrap_female > span.num_female")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
	By.cssSelector("div.wrap_male > span.num_male")));

            // 성별 비율
            String femalePercentage = webDriver.findElement(
	By.cssSelector("div.wrap_female > span.num_female")).getText();
            String malePercentage = webDriver.findElement(
	By.cssSelector("div.wrap_male > span.num_male")).getText();

            // 연령대 별 비율
            List<WebElement> rectElements = webDriver.findElements(By.cssSelector(
	"g.highcharts-series-0.highcharts-column-series.highcharts-tracker rect"));
            List<Double> ageGroupRatios = parseSVG(rectElements);

            return new KakaoDataTrendCrawlingResponse(femalePercentage, malePercentage,
	ageGroupRatios.get(0), ageGroupRatios.get(1), ageGroupRatios.get(2),
	ageGroupRatios.get(3), ageGroupRatios.get(4), ageGroupRatios.get(5));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to crawl kakao data trend");
        } finally {
            if (webDriver != null) {
	webDriver.quit();
            }
        }
    }

    private List<Double> parseSVG(List<WebElement> rectElements) {
        List<Double> ageGroupRatios = new ArrayList<>();

        for (WebElement rect : rectElements) {
            double height = Double.parseDouble(rect.getAttribute("height"));
            ageGroupRatios.add(height);
        }

        return ageGroupRatios;
    }
}
