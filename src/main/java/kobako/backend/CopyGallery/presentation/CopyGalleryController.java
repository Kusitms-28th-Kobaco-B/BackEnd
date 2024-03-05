package kobako.backend.CopyGallery.presentation;

import kobako.backend.CopyGallery.application.CopyGalleryService;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import kobako.backend.global.domain.RequestUri;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = RequestUri.copyGallery)
@RequiredArgsConstructor
public class CopyGalleryController {

    private static CopyGalleryService copyGalleryService;

    // 최근 저장한 광고카피
    @GetMapping("/copies/recent-loaded/{memberId}")
    public ResponseEntity<Slice<AdvertisementCopyResponse>> GetMyRecentCopyGallery(
            @PathVariable Long memberId
    ) {
        Slice<AdvertisementCopyResponse> advertisementCopyResponseSlice
                = copyGalleryService.getRecentLoadAdvertisementCopy(memberId);

        return ResponseEntity.ok(advertisementCopyResponseSlice);
    }




}
