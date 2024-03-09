package kobako.backend.copyGallery.presentation;

import kobako.backend.copyGallery.application.CopyGalleryService;
import kobako.backend.copyGallery.dto.Request.SearchCopyGalleryRequest;
import kobako.backend.copyGallery.dto.Response.CopyGalleryResponse;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import kobako.backend.global.domain.RequestUri;
import kobako.backend.swaggerUi.CopyGalleryUi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = RequestUri.copyGallery)
@RequiredArgsConstructor
public class CopyGalleryController implements CopyGalleryUi {

    private final CopyGalleryService copyGalleryService;

    // 최근 저장한 광고카피
    @GetMapping("/recent-loaded/{memberId}")
    public ResponseEntity<List<AdvertisementCopyResponse>> getMyRecentCopyGallery(
            @PathVariable Long memberId
    ) {
        List<AdvertisementCopyResponse> advertisementCopyResponses
                = copyGalleryService.getRecentLoadAdvertisementCopy(memberId);

        return ResponseEntity.ok(advertisementCopyResponses);
    }

    // 카피 갤러리 검색
    @GetMapping("/search")
    public ResponseEntity<List<CopyGalleryResponse>> GetAllAdvertismentCopies(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Service service,
            @RequestParam(required = false) Tone tone,
            @RequestParam(required = false) String keyword
            ) {

        SearchCopyGalleryRequest searchCopyGalleryRequest
                = new SearchCopyGalleryRequest(startDate, endDate, service, tone, keyword);

        List<CopyGalleryResponse> copyGalleryResponseList
                = copyGalleryService.searchCopyGallery(searchCopyGalleryRequest);

        return ResponseEntity.ok(copyGalleryResponseList);
    }


}
