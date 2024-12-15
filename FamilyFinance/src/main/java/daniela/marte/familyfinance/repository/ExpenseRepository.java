package daniela.marte.familyfinance.repository;

import daniela.marte.familyfinance.pojo.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByFamilyMemberId(Long familyMemberId); // Găsește cheltuieli după ID-ul membrului familiei
}