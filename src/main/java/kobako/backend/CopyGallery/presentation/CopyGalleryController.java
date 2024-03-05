package kobako.backend.CopyGallery.presentation;

import kobako.backend.CopyGallery.application.CopyGalleryService;
import kobako.backend.CopyGallery.dto.Request.SearchCopyGalleryRequest;
import kobako.backend.CopyGallery.dto.Response.CopyGalleryResponse;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import kobako.backend.global.domain.RequestUri;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = RequestUri.copyGallery)
@RequiredArgsConstructor
public class CopyGalleryController {

    private static CopyGalleryService copyGalleryService;

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
    public ResponseEntity<Page<CopyGalleryResponse>> GetAllAdvertismentCopies(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Service service,
            @RequestParam(required = false) Tone tone
            ) {

        SearchCopyGalleryRequest searchCopyGalleryRequest
                = new SearchCopyGalleryRequest(startDate, endDate, service, tone);

        Page<CopyGalleryResponse> copyGalleryResponsePage
                = copyGalleryService.searchCopyGallery(searchCopyGalleryRequest);

        return ResponseEntity.ok(copyGalleryResponsePage);
    }


}
