package ru.itis.financeapi.dto.request;

import java.time.Instant;
import java.util.UUID;

public record GoalAndBudgetLimitUpdateRequest(
        UUID goalAndLimitId,
        int makeMoneyGoal,
        int spendMoneyGoal,
        int budgetLimit,
        Instant startDate,
        Instant endDate
) {
}
