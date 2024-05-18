package ru.itis.financeapi.dto.request;

import java.util.UUID;

public record BudgetDistributionRuleSaveRequest(
        int budget,
        int currentExpenses,
        String category
) {
}
