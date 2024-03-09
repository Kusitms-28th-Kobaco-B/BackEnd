package kobako.backend.crawling.application;

import java.util.ArrayList;
import java.util.List;
import kobako.backend.crawling.presentation.dto.YoutubeCrawlingRequest;
import kobako.backend.crawling.presentation.dto.YoutubeCrawlingResponse;
import kobako.backend.naver.presentation.dto.CharacterAnalysisRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class YoutubeCrawlingService {

    private final String youtubeSearchUrl = "https://www.youtube.com/results?search_query=";

    public ArrayList<YoutubeCrawlingResponse> searchByKeyword(CharacterAnalysisRequest request) {
        System.setProperty("webdriver.chrome.driver", "/Users/gundorit/chromedriver");
        WebDriver driver = new ChromeDriver();
        ArrayList<YoutubeCrawlingResponse> responses = new ArrayList<>();

        try {
            driver.get(youtubeSearchUrl + request.keyword());

//             예: new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.tagName("ytd-video-renderer")));

            List<WebElement> videoElements = driver.findElements(By.tagName("ytd-video-renderer"));

            int count = 0;
            for (WebElement video : videoElements) {
	count++;
	final int numbersOfVideo = 6;
	if (count > numbersOfVideo) {
	    break;
	}

	String title = video.findElement(By.id("video-title")).getAttribute("title");

	WebElement imgElement = video.findElement(By.tagName("img"));
	String imageUrl = imgElement.getAttribute("src");
	if (imageUrl == null || imageUrl.isEmpty()) {
	    imageUrl = imgElement.getAttribute(
	        "data-thumb"); // Fallback for lazy loaded images
	}

	YoutubeCrawlingResponse response = new YoutubeCrawlingResponse(title, imageUrl);
	responses.add(response);
            }
        } finally {
            driver.quit();
        }

        return responses;
    }
}
