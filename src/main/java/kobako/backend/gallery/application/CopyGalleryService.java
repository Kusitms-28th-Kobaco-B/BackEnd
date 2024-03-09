package kobako.backend.gallery.application;

import kobako.backend.gallery.domain.CopyGallery;
import kobako.backend.gallery.dto.request.SearchCopyGalleryRequest;
import kobako.backend.gallery.dto.response.CopyGalleryResponse;
import kobako.backend.gallery.infra.persistence.CopyGalleryRepository;
import kobako.backend.advertisement.dto.response.AdvertisementCopyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        List<CopyGallery> recentAdvertisementCopies = copyGalleryRepository.findByMember_MemberIdOrderByCreatedDateDesc(memberId);

        // List로 반환
        return AdvertisementCopyResponse.ofCopyGalleriesList(recentAdvertisementCopies);
    }

    // 카피 갤러리 검색
    public List<CopyGalleryResponse> searchCopyGallery(
            SearchCopyGalleryRequest searchCopyGalleryRequest
    ) {

        //최근 날짜 순으로 List
        List<CopyGallery> searchedCopyGalleries
                = copyGalleryRepository.findByServiceAndToneAndCreatedDateBetween(
                        searchCopyGalleryRequest.getService(),
                        searchCopyGalleryRequest.getTone(),
                        searchCopyGalleryRequest.getStartDate(),
                        searchCopyGalleryRequest.getEndDate()
        );

        // 각 페이지네이션된 CopyGallery 조회수 1 증가.
        for (CopyGallery copyGallery : searchedCopyGalleries) {
            copyGallery.increaseViews();
            copyGalleryRepository.save(copyGallery);
        }

        return CopyGalleryResponse.ofCopyGalleriesList(searchedCopyGalleries);
    }
}
