package daniela.marte.familyfinance.controllers;

import daniela.marte.familyfinance.interfaces.IFamilyMemberService;
import daniela.marte.familyfinance.pojo.FamilyMember;
import daniela.marte.familyfinance.pojo.FamilyMemberDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FamilyMemberController {

    private final IFamilyMemberService familyMemberService;

    public FamilyMemberController(IFamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @PostMapping("/member")
    public ResponseEntity<?> addMember(@Valid @RequestBody FamilyMemberDTO familyMemberDTO) {
        // Convert DTO to object
        FamilyMember familyMember = new FamilyMember(
                null,
                familyMemberDTO.getFirstName(),
                familyMemberDTO.getLastName(),
                familyMemberDTO.getAge(),
                familyMemberDTO.getRole()
        );
        familyMemberService.addMember(familyMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(familyMember);
    }

    @GetMapping("/members")
    public ResponseEntity<List<FamilyMember>> getAllMembers() {
        return ResponseEntity.ok(familyMemberService.getAllMembers());
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id) {
        if (familyMemberService.deleteMember(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Member successfully deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found!");
        }
    }
}
