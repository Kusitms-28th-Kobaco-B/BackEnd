package kobako.backend.swaggerUi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import kobako.backend.copyGallery.dto.Response.CopyGalleryResponse;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "카피 갤러리", description = "광고 카피 갤러리 API")
public interface CopyGalleryUi {

    @Operation(summary = "최근 저장한 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "광고카피가 없음")
    })
    @GetMapping("/recent-loaded/{memberId}")
    ResponseEntity<List<AdvertisementCopyResponse>> getMyRecentCopyGallery(
            @Parameter(description = "회원 ID", required = true) @PathVariable Long memberId
    );

    @Operation(summary = "카피 갤러리 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @GetMapping("/search")
    ResponseEntity<List<CopyGalleryResponse>> GetAllAdvertismentCopies(
            @Parameter(description = "시작 날짜") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "종료 날짜") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @Parameter(description = "서비스") @RequestParam(required = false) Service service,
            @Parameter(description = "톤앤매너") @RequestParam(required = false) Tone tone,
            @Parameter(description = "키워드") @RequestParam(required = false) String keyword
    );

}