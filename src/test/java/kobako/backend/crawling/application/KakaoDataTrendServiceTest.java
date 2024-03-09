package kobako.backend.crawling.application;

import static org.junit.jupiter.api.Assertions.*;

import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingRequest;
import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingResponse;
import kobako.backend.naver.presentation.dto.CharacterAnalysisRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoDataTrendServiceTest {

    @Autowired
    private KakaoDataTrendService kakaoDataTrendService;

    @Test
    void 카카오_데이터_트렌드에서_원하는_정보를_추출한다() {
        // given
        CharacterAnalysisRequest request = new CharacterAnalysisRequest("20230302",
            "20240302", "이민정");

        // when
        KakaoDataTrendCrawlingResponse response = kakaoDataTrendService.crawlKakaoDataTrend(
            request);

        // then
        assertNotNull(response);
        System.out.print(response.toString());
    }

}