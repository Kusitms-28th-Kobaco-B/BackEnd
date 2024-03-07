package kobako.backend.myPage.application;

import kobako.backend.advertisementCopy.dto.response.AdvertisementCopyResponse;
import kobako.backend.copyGallery.domain.CopyGallery;
import kobako.backend.copyGallery.dto.Request.SearchCopyGalleryRequest;
import kobako.backend.copyGallery.dto.Response.CopyGalleryResponse;
import kobako.backend.copyGallery.infra.presistence.CopyGalleryRepository;
import kobako.backend.myPage.dto.Request.GetMyCopiesRequest;
import kobako.backend.myPage.dto.Response.GetMyCopiesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MyPageService {

    private final CopyGalleryRepository copyGalleryRepository;




    // 마이 카피 탐색
    public Page<GetMyCopiesResponse> getMyCopies(
            GetMyCopiesRequest getMyCopiesRequest
    ) {
        //최근 날짜 순으로 6개 Page.
        Pageable pageable = PageRequest.of(0, 6, Sort.by("createdDate").descending());
        Page<CopyGallery> myCopiesPage
                = copyGalleryRepository.findByMember_MemberIdAndServiceAndToneAndKeywordsContainingOrderByCreatedDateDesc(
                        getMyCopiesRequest.getMemberId(),
                        getMyCopiesRequest.getService(),
                        getMyCopiesRequest.getTone(),
                        getMyCopiesRequest.getKeyword(),
                        pageable
        );

        return GetMyCopiesResponse.ofMyCopiesPage(myCopiesPage);
    }
}
