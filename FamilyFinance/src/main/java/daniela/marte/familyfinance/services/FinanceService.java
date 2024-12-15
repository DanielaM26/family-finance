package daniela.marte.familyfinance.services;

import daniela.marte.familyfinance.interfaces.IFinanceService;
import daniela.marte.familyfinance.pojo.Expense;
import daniela.marte.familyfinance.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService implements IFinanceService {

    final private ExpenseRepository expenseRepository;

    public FinanceService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense addExpense(Expense expense) {
        if (expense.getFamilyMember() == null || expense.getFamilyMember().getId() == null) {
            throw new IllegalArgumentException("FamilyMember ID is required!");
        }
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getExpenses() {
        return this.expenseRepository.findAll();
    }

    @Override
    public boolean deleteExpense(Long id) {
        this.expenseRepository.deleteById(id);
        return true;
    }
}
