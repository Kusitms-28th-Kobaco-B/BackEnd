package kobako.backend.advertisementCopy.infra.presistence;

import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementCopyRepository extends JpaRepository<AdvertisementCopy, Long> {

}
