package ru.itis.financeapi.dto.request;

import java.time.Instant;

public record GoalAndBudgetLimitUpdateRequest(
        int makeMoneyGoal,
        int spendMoneyGoal,
        int budgetLimit,
        Instant startDate,
        Instant endDate
) {
}
