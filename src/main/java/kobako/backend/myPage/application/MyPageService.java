package kobako.backend.myPage.application;

import kobako.backend.gallery.domain.CopyGallery;
import kobako.backend.gallery.infra.persistence.CopyGalleryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import kobako.backend.myPage.dto.request.GetMyCopiesRequest;
import kobako.backend.myPage.dto.response.GetMyCopiesResponse;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MyPageService {

    private final CopyGalleryRepository copyGalleryRepository;




    // 마이 카피 탐색
    public List<GetMyCopiesResponse> getMyCopies(
            GetMyCopiesRequest getMyCopiesRequest
    ) {
        //최근 날짜 순으로 List
        List<CopyGallery> myCopiesPage
                = copyGalleryRepository.findByMember_MemberIdAndServiceAndToneOrderByCreatedDateDesc(
                        getMyCopiesRequest.getMemberId(),
                        getMyCopiesRequest.getService(),
                        getMyCopiesRequest.getTone()
        );

        return GetMyCopiesResponse.ofMyCopiesList(myCopiesPage);
    }
}
