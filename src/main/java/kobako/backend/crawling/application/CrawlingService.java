package kobako.backend.crawling.application;

import java.util.ArrayList;
import java.util.List;
import kobako.backend.crawling.presentation.dto.response.BrandReputationCrawlingResponse;
import kobako.backend.crawling.presentation.dto.response.KakaoDataTrendCrawlingResponse;
import kobako.backend.crawling.presentation.dto.response.YoutubeCrawlingResponse;
import kobako.backend.naver.application.DataLabService;
import kobako.backend.naver.presentation.dto.request.CharacterAnalysisRequest;
import kobako.backend.naver.presentation.dto.response.DatalabSearchResponse;
import kobako.backend.trend.presentation.dto.response.CharacterAnalysisResponse;
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

    public List<YoutubeCrawlingResponse> getYoutubeInformation(String keyword) {
        return youtubeCrawlingService.searchByKeyword(keyword);
    }
}
