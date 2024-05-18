package ru.itis.financeapi.dto.request;

public record BudgetDistributionRuleSaveRequest(
        int budget,
        int currentExpenses,
        String category
) {
}
