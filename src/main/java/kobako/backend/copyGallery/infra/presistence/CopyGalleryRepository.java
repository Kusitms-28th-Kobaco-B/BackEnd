package kobako.backend.copyGallery.infra.presistence;

import kobako.backend.copyGallery.domain.CopyGallery;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CopyGalleryRepository extends JpaRepository<CopyGallery, Long> {

    // 해당 사용자가 저장한 광고카피 최신순으로 탐색
    List<CopyGallery> findByMember_MemberIdOrderByCreatedDateDesc(Long memberId);

    //조건에 맞는 카피갤러리 탐색
    Page<CopyGallery> findByServiceAndToneAndCreatedDateBetween(Service service, Tone tone, LocalDate startDate, LocalDate endDate, Pageable pageable);

    //조건에 맞는 마이카피 탐색
    Page<CopyGallery> findByMember_MemberIdAndServiceAndToneOrderByCreatedDateDesc(Long memberId, Service service, Tone tone, Pageable pageable);
}
