package daniela.marte.familyfinance.repository;

import daniela.marte.familyfinance.pojo.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {}

