package kobako.backend.crawling.application;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import kobako.backend.crawling.domain.BrandReputation;
import kobako.backend.crawling.presentation.dto.SometrendRequest;
import kobako.backend.naver.presentation.dto.CharacterAnalysisRequest;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
public class SometrendService {

    private final String sometrendBaseUrl = "https://some.co.kr/analysis/social/association";

    public ArrayList<String> crawlSometrend(CharacterAnalysisRequest request) {
        WebDriver webDriver = null;
        ArrayList<String> associatedKeywords = new ArrayList<>();
        try {
            final String url = sometrendBaseUrl + "?keyword=" + request.keyword() + "&startDate="
	+ request.startDate() + "&endDate=" + request.endDate() + "&sources=blog";
            System.setProperty("webdriver.chrome.driver", "/Users/gundorit/chromedriver");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-agent=Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Mobile Safari/537.36");

            webDriver = new ChromeDriver(options);
            webDriver.get(url);

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(1000));
            List<WebElement> gElements = webDriver.findElements(By.cssSelector(
	"g.association_node"
            ));

            for (WebElement textElement : gElements) {
	String text = textElement.getAttribute("textContent");
	if (text != null) {
	    associatedKeywords.add(text);
	}
            }

            return associatedKeywords;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to crawl kakao data trend");
        } finally {
            if (webDriver != null) {
	webDriver.quit();
            }
        }
    }

    public Document jsoup(SometrendRequest request) {
        final String url = sometrendBaseUrl + "?keyword=" + request.keyword() + "&startDate="
            + request.startDate() + "&endDate=" + request.endDate() + "&sources=blog";
        System.setProperty("webdriver.chrome.driver", "/Users/gundorit/chromedriver");

        try {
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();

            return document;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to crawl brand reputation");
        }

    }
}
