package kobako.backend.CopyGallery.infra.presistence;

import kobako.backend.CopyGallery.domain.CopyGallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyGalleryRepository extends JpaRepository<CopyGallery, Long> {

}
