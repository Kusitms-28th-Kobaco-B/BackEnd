package kobako.backend.crawling.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import kobako.backend.crawling.application.BrandReputationCrawlingRequest;
import kobako.backend.crawling.application.CrawlingService;
import kobako.backend.crawling.domain.BrandReputation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crawling")
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping("/brand-reputation/{year}/{month}")
    @Operation(summary = "브랜드 평판 순위 조회", description = "브랜드 평판 순위를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })
    public ResponseEntity<List<BrandReputation>> crawlBrandReputation(
        @RequestParam(name = "year") String year,
        @RequestParam(name = "month") String month) {
        return ResponseEntity.ok(
            crawlingService.crawlBrandReputation(new BrandReputationCrawlingRequest(year, month)));
    }
}
