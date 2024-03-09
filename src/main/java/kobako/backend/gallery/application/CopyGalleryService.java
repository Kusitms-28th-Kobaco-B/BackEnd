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
        List<CopyGallery> searchedCopyGalleries;

        /// 키워드 리스트가 비어 있는지 확인
        if (searchCopyGalleryRequest.getKeywords() == null || searchCopyGalleryRequest.getKeywords().isEmpty()) {
            // 키워드 리스트가 비어 있으면 키워드를 검색 조건에서 제외
            searchedCopyGalleries = copyGalleryRepository.findByServiceAndToneAndCreatedDateBetween(
                    searchCopyGalleryRequest.getService(),
                    searchCopyGalleryRequest.getTone(),
                    searchCopyGalleryRequest.getStartDate(),
                    searchCopyGalleryRequest.getEndDate()
            );
        } else {
            // 키워드 리스트가 비어 있지 않으면 기존 로직 사용
            searchedCopyGalleries = copyGalleryRepository.findByServiceAndToneAndCreatedDateBetweenAndKeywordsIn(
                    searchCopyGalleryRequest.getService(),
                    searchCopyGalleryRequest.getTone(),
                    searchCopyGalleryRequest.getStartDate(),
                    searchCopyGalleryRequest.getEndDate(),
                    searchCopyGalleryRequest.getKeywords()
            );
        }

        // 각 페이지네이션된 CopyGallery 조회수 1 증가.
        for (CopyGallery copyGallery : searchedCopyGalleries) {
            copyGallery.increaseViews();
            copyGalleryRepository.save(copyGallery);
        }

        return CopyGalleryResponse.ofCopyGalleriesList(searchedCopyGalleries);
    }
}
