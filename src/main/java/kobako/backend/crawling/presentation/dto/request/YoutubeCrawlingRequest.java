package kobako.backend.crawling.presentation.dto.request;

import org.springframework.util.Assert;

public record YoutubeCrawlingRequest(String keyword, int numberOfVideos) {

    public YoutubeCrawlingRequest {
        Assert.notNull(keyword, "keyword must not be null");
        Assert.isTrue(numberOfVideos > 0 && numberOfVideos < 11,
            "numberOfVideos must be greater than 0 and smaller than 11");
    }
}
