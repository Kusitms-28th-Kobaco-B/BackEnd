package kobako.backend.advertisement.infra.persistence;

import kobako.backend.advertisement.domain.AdvertisementCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdvertisementCopyRepository extends JpaRepository<AdvertisementCopy, Long> {

    // 해당 사용자의 가장 최근 작성한 광고카피 8개
    List<AdvertisementCopy> findByMember_MemberIdOrderByCreatedDateDesc(Long memberId);

    Optional<AdvertisementCopy> findByMember_MemberIdAndAdvertisementCopyId(Long memberId, Long advertisementCopyId);

}
