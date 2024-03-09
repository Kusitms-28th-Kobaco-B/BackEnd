package kobako.backend.trend.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kobako.backend.crawling.application.CrawlingService;
import kobako.backend.global.domain.RequestUri;
import kobako.backend.naver.presentation.dto.CharacterAnalysisRequest;
import kobako.backend.trend.presentation.dto.CharacterAnalysisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RequestUri.trend)
@RequiredArgsConstructor
public class TrendController {

    private final CrawlingService crawlingService;

    @PostMapping("/character")
    @Operation(summary = "브랜드 평판 순위 조회", description = "브랜드 평판 순위를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })
    public ResponseEntity<CharacterAnalysisResponse> characterAnalysis(@RequestBody
    CharacterAnalysisRequest request) {
        return ResponseEntity.ok(crawlingService.getCharacterAnalysis(request));
    }
}
