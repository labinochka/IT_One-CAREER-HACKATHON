package ru.itis.financeapi.dto.response;

import java.time.Instant;

public record GoalAndBudgetLimitWithProgressResponse(
        int makeMoneyGoal,
        int spendMoneyGoal,
        int budgetLimit,
        Instant startDate,
        Instant endDate,
        int progressByMakeMoney,
        int progressBySpendMoney
) {
}
