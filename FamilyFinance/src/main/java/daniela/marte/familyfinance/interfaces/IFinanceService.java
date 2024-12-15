package daniela.marte.familyfinance.interfaces;

import daniela.marte.familyfinance.pojo.Expense;

import java.util.List;

public interface IFinanceService {
    Expense addExpense(Expense expense);
    List<Expense> getExpenses();
    boolean deleteExpense(Long id);
}
