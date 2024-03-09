package kobako.backend.copyGallery.infra.presistence;

import kobako.backend.copyGallery.domain.CopyGallery;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CopyGalleryRepository extends JpaRepository<CopyGallery, Long> {

    // 해당 사용자가 저장한 광고카피 최신순으로 탐색
    List<CopyGallery> findByMember_MemberIdOrderByCreatedDateDesc(Long memberId);

    // 이미 저장된 광고카피인지 탐색
    Optional<CopyGallery> findByAdvertisementCopy_AdvertisementCopyId(Long advertisementCopyId);

    //조건에 맞는 카피갤러리 탐색
    List<CopyGallery> findByServiceAndToneAndCreatedDateBetween(Service service, Tone tone, LocalDate startDate, LocalDate endDate);


    //조건에 맞는 마이카피 탐색
    List<CopyGallery> findByMember_MemberIdAndServiceAndToneOrderByCreatedDateDesc(Long memberId, Service service, Tone tone);

}
