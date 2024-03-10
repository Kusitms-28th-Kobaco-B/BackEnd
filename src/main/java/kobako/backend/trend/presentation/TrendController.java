package kobako.backend.trend.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import kobako.backend.crawling.application.CrawlingService;
import kobako.backend.crawling.presentation.dto.response.BrandReputationCrawlingResponse;
import kobako.backend.crawling.presentation.dto.response.YoutubeCrawlingResponse;
import kobako.backend.global.domain.RequestUri;
import kobako.backend.naver.presentation.dto.request.CharacterAnalysisRequest;
import kobako.backend.trend.presentation.dto.response.CharacterAnalysisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestUri.trend)
@RequiredArgsConstructor
@CrossOrigin(origins = "https://kobaco.vercel.app", allowedHeaders = "*")
public class TrendController {

    private final CrawlingService crawlingService;

    @PostMapping("/character")
    @Operation(summary = "인물 분석 조회", description = "인물 분석 정보를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })
    public ResponseEntity<CharacterAnalysisResponse> characterAnalysis(@RequestBody
    CharacterAnalysisRequest request) {
        return ResponseEntity.ok(crawlingService.getCharacterAnalysis(request));
    }

    @PostMapping("/brand-reputation")
    @Operation(summary = "브랜드 평판 조회", description = "브랜드 평판 정보를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })
    public ResponseEntity<BrandReputationCrawlingResponse> brandReputation(@RequestBody
    CharacterAnalysisRequest request) {
        return ResponseEntity.ok(crawlingService.getBrandReputation(request));
    }

    @PostMapping("/youtube")
    @Operation(summary = "키워드 기반 유튜브 조회", description = "키워드와 관련된 유튜브 콘텐츠를 추천합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })
    public ResponseEntity<List<YoutubeCrawlingResponse>> youtube(@RequestBody String keyword) {
        return ResponseEntity.ok(crawlingService.getYoutubeInformation(keyword));
    }
}
