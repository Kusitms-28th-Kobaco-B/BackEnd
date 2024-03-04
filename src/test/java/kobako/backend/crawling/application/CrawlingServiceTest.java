package kobako.backend.crawling.application;

import static org.junit.jupiter.api.Assertions.*;

import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingRequest;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingResponse;
import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingRequest;
import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrawlingServiceTest {

    @Autowired
    private CrawlingService crawlingService;

    @Test
    void 카카오_데이터_트렌드에서_원하는_정보를_추출한다() {
        // given
        KakaoDataTrendCrawlingRequest request = new KakaoDataTrendCrawlingRequest("20230302",
            "20240302", "이민정");

        // when
        KakaoDataTrendCrawlingResponse response = crawlingService.crawlKakaoDataTrend(request);

        // then
        assertNotNull(response);
        System.out.print(response.toString());
    }

    @Test
    void 키워드를_받으면_제일_높은_순위와_카테고리를_반환한다() {
        // given
        String keyword = "이민정";
        BrandReputationCrawlingRequest request = new BrandReputationCrawlingRequest(keyword);

        // when
        BrandReputationCrawlingResponse response = crawlingService.getHighestBrandReputationRank(request);

        // then
    }
}