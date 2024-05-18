package ru.itis.financeimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeimpl.model.GoalAndBudgetLimit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoalAndBudgetLimitRepository extends JpaRepository<GoalAndBudgetLimit, UUID> {
    Optional<List<GoalAndBudgetLimit>> findAllByAccount_Id(UUID accountId);
}
