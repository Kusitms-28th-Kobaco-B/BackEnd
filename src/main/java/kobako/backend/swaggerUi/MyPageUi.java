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
import kobako.backend.myPage.dto.Response.GetMyCopiesResponse;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "마이페이지", description = "마이페이지 API")
public interface MyPageUi {

    @Operation(summary = "내가 생성한 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @GetMapping("/myCopies")
    ResponseEntity<Page<GetMyCopiesResponse>> GetMyCopyGallery(
            @Parameter(description = "회원 ID", required = true) @RequestParam Long memberId,
            @Parameter(description = "서비스") @RequestParam(required = false) Service service,
            @Parameter(description = "톤앤매너") @RequestParam(required = false) Tone tone
    );

}
