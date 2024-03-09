package kobako.backend.crawling.application;

import java.util.List;
import kobako.backend.crawling.domain.BrandReputation;
import kobako.backend.crawling.presentation.dto.response.BrandReputationCrawlingResponse;
import kobako.backend.naver.presentation.dto.request.CharacterAnalysisRequest;
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
        CharacterAnalysisRequest request = new CharacterAnalysisRequest("20240101", "20240301",
            "손흥민");

        // when
        String baseUrl = "/ad";
        String category = "광고모델 부문";
        List<BrandReputation> response = brandReputationCrawlingService.getBrandReputationRank(baseUrl,
            category,
            request);

        // then
        Assertions.assertThat(response).isNotNull();
        for(BrandReputation brandReputation : response){
            System.out.println(brandReputation.getDescription()+" "+brandReputation.getRank()+" "+ brandReputation.getName());
        }
    }

    @Test
    void 속한_카테고리_중_가장_높은_순위를_반환한다() {
        // given
        CharacterAnalysisRequest request = new CharacterAnalysisRequest("20240101", "20240301",
            "유재석");

        // when
        BrandReputationCrawlingResponse response = brandReputationCrawlingService.getHighestBrandReputation(
            request);

        // then
        Assertions.assertThat(response.rank()).isNotNull();
    }
}