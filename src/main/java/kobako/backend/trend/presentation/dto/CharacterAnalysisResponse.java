package kobako.backend.trend.presentation.dto;

import java.util.ArrayList;
import java.util.List;
import kobako.backend.crawling.presentation.dto.BrandReputationCrawlingResponse;
import kobako.backend.crawling.presentation.dto.KakaoDataTrendCrawlingResponse;
import kobako.backend.naver.domain.AgeRatio;
import kobako.backend.naver.domain.SearchVolume;
import kobako.backend.naver.presentation.dto.DatalabSearchResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CharacterAnalysisResponse {

    private List<String> recentKeywords;
    private List<SearchVolume> searchVolumes;
    private String maleRatio;
    private String femaleRatio;
    private List<AgeRatio> ageRatios;
    private BrandReputationCrawlingResponse brandReputationResponse;
    private List<String> associatedKeywords;

    @Getter
    @Setter
    static class Youtube {

        private String imageUrl;
        private String title;
        private String date;
    }

    public static CharacterAnalysisResponse of(DatalabSearchResponse searchResponse,
        KakaoDataTrendCrawlingResponse kakaoDataTrendCrawlingResponse,
        BrandReputationCrawlingResponse brandReputationCrawlingResponse,
        ArrayList<String> associatedKeywords) {
        return new CharacterAnalysisResponse(null,
            searchResponse.getSearchVolumes(),
            kakaoDataTrendCrawlingResponse.maleRatio(),
            kakaoDataTrendCrawlingResponse.femaleRatio(),
            List.of(
	AgeRatio.of("10대", kakaoDataTrendCrawlingResponse.teenager()),
	AgeRatio.of("20대", kakaoDataTrendCrawlingResponse.twenties()),
	AgeRatio.of("30대", kakaoDataTrendCrawlingResponse.thirties()),
	AgeRatio.of("40대", kakaoDataTrendCrawlingResponse.forties()),
	AgeRatio.of("50대", kakaoDataTrendCrawlingResponse.fifties()),
	AgeRatio.of("60대", kakaoDataTrendCrawlingResponse.sixties())),
            brandReputationCrawlingResponse,
            associatedKeywords);
    }
}
