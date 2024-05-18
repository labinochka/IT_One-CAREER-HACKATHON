package ru.itis.financeimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.financeimpl.model.BudgetDistributionRule;

import java.util.UUID;

@Repository
public interface BudgetDistributionRuleRepository extends JpaRepository<BudgetDistributionRule, UUID> {

}
