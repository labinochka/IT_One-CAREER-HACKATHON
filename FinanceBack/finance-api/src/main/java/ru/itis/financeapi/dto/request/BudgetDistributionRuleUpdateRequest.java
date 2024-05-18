package ru.itis.financeapi.dto.request;

import java.util.UUID;

public record BudgetDistributionRuleUpdateRequest(
        UUID ruleId,
        int budget,
        String category
) {
}
