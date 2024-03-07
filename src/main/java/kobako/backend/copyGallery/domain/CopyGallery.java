package kobako.backend.copyGallery.domain;

import jakarta.persistence.*;
import kobako.backend.member.domain.Member;
import kobako.backend.advertisementCopy.domain.AdvertisementCopy;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.Tone;
import kobako.backend.global.base.BaseEntity;
import lombok.*;

import java.util.List;

@Entity(name = "COPY_GALLERY")
@Getter
@Setter
@NoArgsConstructor
public class CopyGallery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "copy_gallery_id")
    private Long copyGalleryId;

    @ManyToOne
    @JoinColumn(name = "advertisement_copy_id")
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

    @ElementCollection
    private List<String> keywords;


    @Builder
    public CopyGallery(Long copyGalleryId, AdvertisementCopy advertisementCopy, Member member, Service service, String productName, Tone tone, Long views, String message, List<String> keywords) {
        super();
        this.copyGalleryId = copyGalleryId;
        this.advertisementCopy = advertisementCopy;
        this.member = member;
        this.service = service;
        this.productName = productName;
        this.tone = tone;
        this.views = views;
        this.message = message;
        this.keywords = keywords;
    }



    // 조회수 증가 메서드
    public void increaseViews() {
        this.views++;
    }
}
