package ru.itis.financeapi.dto.response;

import java.time.Instant;

public record GoalAndBudgetWithProgressResponse(
        int makeMoneyGoal,
        int spendMoneyGoal,
        int budgetLimit,
        Instant startDate,
        Instant endDate,
        int progressByMakeMoney,
        int progressBySpendMoney
) {
}
