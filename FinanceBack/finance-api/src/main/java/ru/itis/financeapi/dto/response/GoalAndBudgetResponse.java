package ru.itis.financeapi.dto.response;

import java.time.Instant;

public record GoalAndBudgetResponse(
        int makeMoneyGoal,
        int spendMoneyGoal,
        int budgetLimit,
        Instant startDate,
        Instant endDate
) {
}
