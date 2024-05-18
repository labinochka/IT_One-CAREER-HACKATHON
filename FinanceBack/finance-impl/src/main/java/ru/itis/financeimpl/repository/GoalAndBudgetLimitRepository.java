package ru.itis.financeimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.financeimpl.model.GoalAndBudgetLimit;

import java.util.UUID;

public interface GoalAndBudgetLimitRepository extends JpaRepository<GoalAndBudgetLimit, UUID> {
}
