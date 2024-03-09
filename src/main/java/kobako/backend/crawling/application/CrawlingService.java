package kobako.backend.crawling.application;

import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingRequest;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlingService {

    private final BrandReputationCrawlingService brandReputationCrawlingService;
    private final KakaoDataTrendService kakaoDataTrendService;

    public BrandReputationCrawlingResponse getHighestBrandReputationRank(
        BrandReputationCrawlingRequest request) {
        return brandReputationCrawlingService.getHighestBrandReputation(request);
    }
}
