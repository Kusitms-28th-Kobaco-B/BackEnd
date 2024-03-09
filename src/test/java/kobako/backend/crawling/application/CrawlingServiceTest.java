package kobako.backend.crawling.application;

import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingRequest;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrawlingServiceTest {

    @Autowired
    private CrawlingService crawlingService;

    @Test
    void 키워드를_받으면_제일_높은_순위와_카테고리를_반환한다() {
        // given
        String keyword = "이민정";
        BrandReputationCrawlingRequest request = new BrandReputationCrawlingRequest(keyword);

        // when
        BrandReputationCrawlingResponse response = crawlingService.getHighestBrandReputationRank(
            request);

        // then
    }
}