package kobako.backend.advertisement.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import kobako.backend.member.domain.Member;
import kobako.backend.global.enums.Service;
import kobako.backend.global.enums.TargetAge;
import kobako.backend.global.enums.TargetGender;
import kobako.backend.global.enums.Tone;
import kobako.backend.global.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "ADVERTISEMENT_COPY")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class AdvertisementCopy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_copy_id")
    private Long advertisementCopyId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Service service;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "product_name")
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_gender")
    private TargetGender targetGender;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_age")
    private TargetAge targetAge;

    @Enumerated(EnumType.STRING)
    private Tone tone;

    @ElementCollection
    private List<String> keywords;

    @Column(length = 500)
    private String message;

    @Builder
    public AdvertisementCopy(Long advertisementCopyId, Member member, Service service, String projectName, String productName, TargetGender targetGender, TargetAge targetAge, Tone tone, List<String> keywords, String message) {
        super();
        this.advertisementCopyId = advertisementCopyId;
        this.member = member;
        this.service = service;
        this.projectName = projectName;
        this.productName = productName;
        this.targetGender = targetGender;
        this.targetAge = targetAge;
        this.tone = tone;
        this.keywords = keywords;
        this.message = message;
    }
}
