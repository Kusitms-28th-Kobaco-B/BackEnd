package kobako.backend.crawling.application;

import java.util.ArrayList;
import kobako.backend.crawling.presentation.dto.YoutubeCrawlingRequest;
import kobako.backend.crawling.presentation.dto.YoutubeCrawlingResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YoutubeCrawlingTest {

    @Autowired
    private YoutubeCrawlingService youtubeCrawlingService;

    @Test
    void 키워드_검색_결과에_따라_유튜브_데이터를_크롤링한다() {
        // given
        String keyword = "이민정";
        int numberOfVideos = 6;
        YoutubeCrawlingRequest request = new YoutubeCrawlingRequest(keyword,
            numberOfVideos);

        // when
        ArrayList<YoutubeCrawlingResponse> response = youtubeCrawlingService.searchByKeyword(
            request);

        // then
        Assertions.assertThat(response.size()).isEqualTo(numberOfVideos);
        Assertions.assertThat(response.get(0).getTitle()).isNotEmpty();
    }

}