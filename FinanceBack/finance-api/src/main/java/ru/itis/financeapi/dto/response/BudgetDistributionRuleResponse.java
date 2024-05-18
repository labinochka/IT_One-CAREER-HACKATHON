package ru.itis.financeapi.dto.response;

import java.util.UUID;

public record BudgetDistributionRuleResponse(
        UUID id,
        int budget,
        int currentExpenses,
        String category
) {
}
