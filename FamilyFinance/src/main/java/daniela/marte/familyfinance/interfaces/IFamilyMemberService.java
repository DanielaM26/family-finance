package daniela.marte.familyfinance.interfaces;

import daniela.marte.familyfinance.pojo.FamilyMember;

import java.util.List;

public interface IFamilyMemberService {
    FamilyMember addMember(FamilyMember familyMember);
    List<FamilyMember> getAllMembers();
    boolean deleteMember(Long id);
}
