package kobako.backend.copyGallery.application;

import kobako.backend.copyGallery.domain.CopyGallery;
import kobako.backend.copyGallery.dto.Request.SearchCopyGalleryRequest;
import kobako.backend.copyGallery.dto.Response.CopyGalleryResponse;
import kobako.backend.copyGallery.infra.presistence.CopyGalleryRepository;
import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CopyGalleryService {

    private final CopyGalleryRepository copyGalleryRepository;


    // 최근 저장한 광고카피 목록(카피 갤러리)
    public List<AdvertisementCopyResponse> getRecentLoadAdvertisementCopy (
            Long memberId
    ) {
        // 최신 날짜 순으로 가져옴.
        List<CopyGallery> recentAdvertisementCopies = copyGalleryRepository.findByMemberIdOrderByCreatedDateDesc(memberId);

        // List로 반환
        return AdvertisementCopyResponse.ofCopyGalleriesList(recentAdvertisementCopies);
    }

    // 카피 갤러리 검색
    public Page<CopyGalleryResponse> searchCopyGallery(
            SearchCopyGalleryRequest searchCopyGalleryRequest
    ) {
        //최근 날짜 순으로 6개 Page.
        Pageable pageable = PageRequest.of(0, 6, Sort.by("createdDate").descending());
        Page<CopyGallery> searchedCopyGalleries
                = copyGalleryRepository.findByServiceAndToneAndCreatedAtBetween(
                        searchCopyGalleryRequest.getService(),
                        searchCopyGalleryRequest.getTone(),
                        searchCopyGalleryRequest.getStartDate(),
                        searchCopyGalleryRequest.getEndDate(),
                        pageable
        );

        // 각 페이지네이션된 CopyGallery 조회수 1 증가.
        for (CopyGallery copyGallery : searchedCopyGalleries) {
            copyGallery.increaseViews();
            copyGalleryRepository.save(copyGallery);
        }

        return CopyGalleryResponse.ofCopyGalleriesPage(searchedCopyGalleries);
    }
}
