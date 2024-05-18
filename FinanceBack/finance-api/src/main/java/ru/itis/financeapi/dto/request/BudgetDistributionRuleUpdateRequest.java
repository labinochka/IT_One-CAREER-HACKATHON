package ru.itis.financeapi.dto.request;

import java.util.UUID;

public record BudgetDistributionRuleUpdateRequest(
    int budget,
    int currentExpenses,
    String category
) {
}
