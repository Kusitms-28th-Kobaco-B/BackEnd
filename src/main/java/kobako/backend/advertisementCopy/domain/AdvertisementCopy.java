package kobako.backend.advertisementCopy.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import kobako.backend.member.domain.Member;
import kobako.backend.global.ENUM.Service;
import kobako.backend.global.ENUM.TargetAge;
import kobako.backend.global.ENUM.TargetGender;
import kobako.backend.global.ENUM.Tone;
import kobako.backend.global.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "ADVERTISEMENT_COPY")
@Getter
@Setter
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
}
