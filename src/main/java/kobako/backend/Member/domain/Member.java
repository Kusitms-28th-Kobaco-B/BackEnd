package kobako.backend.Member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kobako.backend.global.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity(name ="MEMBER")
@Getter
@Setter
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String id;

    private String password;
}
