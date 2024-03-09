package kobako.backend.crawling.application;

import java.util.ArrayList;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingRequest;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingResponse;
import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingResponse;
import kobako.backend.crawling.presentation.dto.YoutubeCrawlingResponse;
import kobako.backend.naver.application.DataLabService;
import kobako.backend.naver.presentation.dto.CharacterAnalysisRequest;
import kobako.backend.naver.presentation.dto.DatalabSearchResponse;
import kobako.backend.trend.presentation.dto.CharacterAnalysisResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlingService {

    private final BrandReputationCrawlingService brandReputationCrawlingService;
    private final DataLabService dataLabService;
    private final KakaoDataTrendService kakaoDataTrendService;
    private final SometrendService sometrendService;
    private final YoutubeCrawlingService youtubeCrawlingService;

    public CharacterAnalysisResponse getCharacterAnalysis(CharacterAnalysisRequest request) {
        DatalabSearchResponse searchResponse = dataLabService.search(request);
        KakaoDataTrendCrawlingResponse kakaoDataTrendCrawlingResponse = kakaoDataTrendService.crawlKakaoDataTrend(
            request);
        BrandReputationCrawlingResponse brandReputationCrawlingResponse = brandReputationCrawlingService.getHighestBrandReputation(
            request);
        ArrayList<String> associatedKeywords = sometrendService.crawlSometrend(request);

        return CharacterAnalysisResponse.of(searchResponse, kakaoDataTrendCrawlingResponse,
            brandReputationCrawlingResponse, associatedKeywords);
    }

    public void getYoutubeInformation(CharacterAnalysisRequest request) {
        ArrayList<YoutubeCrawlingResponse> youtubeCrawlingResponse = youtubeCrawlingService.searchByKeyword(
            request);
    }
}
