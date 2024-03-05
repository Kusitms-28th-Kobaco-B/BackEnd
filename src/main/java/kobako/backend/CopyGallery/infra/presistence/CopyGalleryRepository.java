package kobako.backend.CopyGallery.infra.presistence;

import kobako.backend.CopyGallery.domain.CopyGallery;
import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CopyGalleryRepository extends JpaRepository<CopyGallery, Long> {

    // 해당 사용자가 저장한 광고카피 최신순으로 탐색
    List<CopyGallery> findByMemberIdOrderByCreatedDateDesc(Long memberId);

    //조건에 맞는 카피갤러리 탐색
    Page<CopyGallery> findByServiceAndToneAndCreatedAtBetween(Service service, Tone tone, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
