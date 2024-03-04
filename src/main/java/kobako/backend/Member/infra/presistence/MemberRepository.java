package kobako.backend.Member.infra.presistence;

import kobako.backend.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
