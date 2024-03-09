package kobako.backend.gallery.infra.persistence;

import kobako.backend.gallery.domain.CopyGallery;
import kobako.backend.global.enums.Service;
import kobako.backend.global.enums.Tone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CopyGalleryRepository extends JpaRepository<CopyGallery, Long> {

    // 해당 사용자가 저장한 광고카피 최신순으로 탐색
    List<CopyGallery> findByMember_MemberIdOrderByCreatedDateDesc(Long memberId);

    // 이미 저장된 광고카피인지 탐색
    Optional<CopyGallery> findByAdvertisementCopy_AdvertisementCopyId(Long advertisementCopyId);

    //조건에 맞는 카피갤러리 탐색
    @Query("SELECT c FROM COPY_GALLERY c WHERE " +
            "(:service IS NULL OR c.service = :service) OR " +
            "(:tone IS NULL OR c.tone = :tone) OR " +
            "(:startDate IS NULL OR c.createdDate >= :startDate) OR " +
            "(:endDate IS NULL OR c.createdDate <= :endDate)")
    List<CopyGallery> findByServiceAndToneAndCreatedDateBetween(@Param("service") Service service, @Param("tone") Tone tone, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);



    //조건에 맞는 마이카피 탐색
    List<CopyGallery> findByMember_MemberIdAndServiceAndToneOrderByCreatedDateDesc(Long memberId, Service service, Tone tone);

}
