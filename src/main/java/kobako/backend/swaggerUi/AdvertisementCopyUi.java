package kobako.backend.swaggerUi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kobako.backend.advertisementCopy.dto.request.GenerateAdvertisementCopyRequest;
import kobako.backend.advertisementCopy.dto.request.UpdateAdvertisementCopyRequest;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "광고카피", description = "광고카피 관련 API")
public interface AdvertisementCopyUi {

    @Operation(summary = "최근 생성한 광고카피 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "광고카피가 없음")
    })
    @GetMapping("/copies/recent/{memberId}")
    ResponseEntity<List<AdvertisementCopyResponse>> getMyAdvertisementCopies(
            @Parameter(description = "회원 ID", required = true) @PathVariable Long memberId
    );

    @Operation(summary = "새로운 광고카피 생성")
    @PostMapping("/copies/{memberId}")
    ResponseEntity<AdvertisementCopyResponse> generateAdvertisementCopy(
            @PathVariable Long memberId,
            @RequestBody GenerateAdvertisementCopyRequest generateAdvertisementCopyRequest
    );

    @Operation(summary = "카피갤러리에 광고카피 저장")
    @PostMapping("/copies/{memberId}/{advertisementCopyId}")
    ResponseEntity<AdvertisementCopyResponse> LoadAdvertisementCopy(
            @Parameter(description = "회원 ID", required = true) @PathVariable Long memberId,
            @Parameter(description = "광고카피 ID", required = true) @PathVariable Long advertisementCopyId
    );

    @Operation(summary = "광고카피 수정")
    @PatchMapping("/copies/{advertisementCopyId}")
    ResponseEntity<AdvertisementCopyResponse> updateAdvertisementCopy(
            @Parameter(description = "광고카피 ID", required = true) @PathVariable Long advertisementCopyId,
            @RequestBody UpdateAdvertisementCopyRequest updateAdvertisementCopyRequest
    );

}
