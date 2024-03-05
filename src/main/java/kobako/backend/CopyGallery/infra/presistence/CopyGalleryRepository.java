package kobako.backend.CopyGallery.infra.presistence;

import kobako.backend.CopyGallery.domain.CopyGallery;
import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyGalleryRepository extends JpaRepository<CopyGallery, Long> {

    Slice<CopyGallery> findByMemberIdOrderByCreatedDateDesc(Long memberId, Pageable pageable);
}
