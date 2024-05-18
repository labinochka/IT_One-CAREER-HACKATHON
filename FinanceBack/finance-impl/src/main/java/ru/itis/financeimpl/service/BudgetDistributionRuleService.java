package ru.itis.financeimpl.service;

import ru.itis.financeapi.dto.request.BudgetDistributionRuleSaveRequest;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleUpdateRequest;
import ru.itis.financeapi.dto.response.BudgetDistributionRuleResponse;

import java.util.Set;
import java.util.UUID;

public interface BudgetDistributionRuleService {
    void create(BudgetDistributionRuleSaveRequest saveRequest);

    void update(BudgetDistributionRuleUpdateRequest updateRequest);

    Set<BudgetDistributionRuleResponse> getAll();

    void delete(UUID id);
}
