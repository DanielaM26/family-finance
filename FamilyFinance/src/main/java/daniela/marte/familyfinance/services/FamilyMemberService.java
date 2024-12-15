package daniela.marte.familyfinance.services;

import daniela.marte.familyfinance.interfaces.IFamilyMemberService;
import daniela.marte.familyfinance.pojo.FamilyMember;
import daniela.marte.familyfinance.repository.FamilyMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberService implements IFamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;

    public FamilyMemberService(FamilyMemberRepository familyMemberRepository) {
        this.familyMemberRepository = familyMemberRepository;
    }

    @Override
    public FamilyMember addMember(FamilyMember familyMember) {
        return familyMemberRepository.save(familyMember);
    }

    @Override
    public List<FamilyMember> getAllMembers() {
        return familyMemberRepository.findAll();
    }

    @Override
    public boolean deleteMember(Long id) {
        if (familyMemberRepository.existsById(id)) {
            familyMemberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
