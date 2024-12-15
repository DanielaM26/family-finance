package daniela.marte.familyfinance.controllers;

import daniela.marte.familyfinance.interfaces.IFinanceService;
import daniela.marte.familyfinance.pojo.Expense;
import daniela.marte.familyfinance.pojo.ExpenseDTO;
import daniela.marte.familyfinance.pojo.FamilyMember;
import daniela.marte.familyfinance.repository.FamilyMemberRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FinanceController {
    private final IFinanceService financeService;
    private final FamilyMemberRepository familyMemberRepository;

    public FinanceController(IFinanceService financeService, FamilyMemberRepository familyMemberRepository) {
        this.financeService = financeService;
        this.familyMemberRepository = familyMemberRepository;
    }

    @PostMapping("/expense")
    public ResponseEntity<?> addExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        FamilyMember familyMember = familyMemberRepository.findById(expenseDTO.getFamilyMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Family member not found"));

        // Map DTO to Entity
        Expense expense = new Expense(
                null,
                expenseDTO.getDescription(),
                expenseDTO.getPrice(),
                expenseDTO.getQuantity(),
                familyMember
        );

        // Save expense
        financeService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }


    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getExpenses() {
        return ResponseEntity.ok(financeService.getExpenses());
    }

    @DeleteMapping("/expense/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable("id") Long id) {
        if(financeService.deleteExpense(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Resource successfully deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID doesn't exist!");
        }
    }
}
