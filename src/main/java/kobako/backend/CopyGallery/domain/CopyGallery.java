package kobako.backend.CopyGallery.domain;

import jakarta.persistence.*;
import kobako.backend.Member.domain.Member;
import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import kobako.backend.global.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "COPY_GALLERY")
@Getter
@Setter
@Builder
public class CopyGallery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "copy_gallery_id")
    private Long copyGalleryId;

    @ManyToOne
    @JoinColumn(name = "advertisementCopy_id")
    private AdvertisementCopy advertisementCopy;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Service service;

    @Column(name = "product_name", length = 20)
    private String productName;

    @Enumerated(EnumType.STRING)
    private Tone tone;

    private Long views;

    @Column(length = 300)
    private String message;

    // 조회수 증가 메서드
    public void increaseViews() {
        this.views++;
    }
}
