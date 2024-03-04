package kobako.backend.advertisementCopy.infra.presistence;

import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementCopyRepository extends JpaRepository<AdvertisementCopy, Long> {

    // 해당 사용자의 가장 최근 작성한 광고카피 8개
    List<AdvertisementCopy> findFirst8ByOrderByCreatedDateDesc(Long memberId);
}
