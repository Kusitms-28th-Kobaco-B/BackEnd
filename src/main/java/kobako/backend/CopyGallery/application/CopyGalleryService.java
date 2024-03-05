package kobako.backend.CopyGallery.application;

import kobako.backend.CopyGallery.domain.CopyGallery;
import kobako.backend.CopyGallery.dto.Request.SearchCopyGalleryRequest;
import kobako.backend.CopyGallery.dto.Response.CopyGalleryResponse;
import kobako.backend.CopyGallery.infra.presistence.CopyGalleryRepository;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CopyGalleryService {

    private final CopyGalleryRepository copyGalleryRepository;


    // 최근 저장한 광고카피 목록(카피 갤러리)
    public Slice<AdvertisementCopyResponse> getRecentLoadAdvertisementCopy (
            Long memberId
    ) {
        // 최근 날짜 순으로 20개 Slice.
        Pageable pageable = PageRequest.of(0, 20, Sort.by("createdDate").descending());
        Slice<CopyGallery> recentAdvertisementCopiesSlice = copyGalleryRepository.findByMemberIdOrderByCreatedDateDesc(memberId, pageable);
        // Slice로 반환
        return AdvertisementCopyResponse.ofCopyGalleriesSlice(recentAdvertisementCopiesSlice);
    }

    public Page<CopyGalleryResponse> searchCopyGallery(
            SearchCopyGalleryRequest searchCopyGalleryRequest
    ) {

        //최근 날짜 순으로 20개 Page.
        Pageable pageable = PageRequest.of(0, 20, Sort.by("createdDate").descending());
        Page<CopyGallery> searchedCopyGalleries
                = copyGalleryRepository.findByServiceAndToneAndCreatedAtBetween(
                        searchCopyGalleryRequest.getService(),
                        searchCopyGalleryRequest.getTone(),
                        searchCopyGalleryRequest.getStartDate(),
                        searchCopyGalleryRequest.getEndDate(),
                        pageable
        );

        return CopyGalleryResponse.ofCopyGalleriesPage(searchedCopyGalleries);
    }


    // 카피 갤러리 검색
}
