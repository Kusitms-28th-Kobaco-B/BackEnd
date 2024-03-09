package kobako.backend.crawling.application;

import java.net.URLEncoder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingRequest;
import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingResponse;
import kobako.backend.naver.presentation.dto.CharacterAnalysisRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
public class KakaoDataTrendService {

    private final String kakaoDataTrendBaseUri = "https://datatrend.kakao.com/result";

    public KakaoDataTrendCrawlingResponse crawlKakaoDataTrend(
        CharacterAnalysisRequest request) {
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

            return new KakaoDataTrendCrawlingResponse(
	femalePercentage.substring(0, femalePercentage.length() - 1),
	malePercentage.substring(0, femalePercentage.length() - 1),
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