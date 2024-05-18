package ru.itis.financeapi.dto.response;

import java.time.Instant;

public record GoalAndBudgetLimitResponse(
        int makeMoneyGoal,
        int spendMoneyGoal,
        int budgetLimit,
        Instant startDate,
        Instant endDate
) {
}
