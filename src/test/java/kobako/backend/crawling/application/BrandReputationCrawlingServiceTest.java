package kobako.backend.crawling.application;

import static org.junit.jupiter.api.Assertions.*;

import kobako.backend.crawling.domain.BrandReputation;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BrandReputationCrawlingServiceTest {

    @Autowired
    private BrandReputationCrawlingService brandReputationCrawlingService;

    @Test
    void 순위권_안에_키워드가_포함되어_있다면_null이_아닌_값을_반환한다() {
        // given
        BrandReputationCrawlingRequest request = new BrandReputationCrawlingRequest("손흥민");

        // when
        String baseUrl = "/rk/ad";
        String category = "광고모델 부문";
        BrandReputation response = brandReputationCrawlingService.getBrandReputationRank(baseUrl,
            category,
            request);

        // then
        Assertions.assertThat(response).isNotNull();
    }
}