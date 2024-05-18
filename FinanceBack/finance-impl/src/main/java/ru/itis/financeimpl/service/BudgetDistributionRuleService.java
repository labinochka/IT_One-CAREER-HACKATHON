package ru.itis.financeimpl.service;

import ru.itis.financeapi.dto.request.BudgetDistributionRuleSaveRequest;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleUpdateRequest;
import ru.itis.financeapi.dto.response.BudgetDistributionRuleResponse;

import java.util.Set;
import java.util.UUID;

public interface BudgetDistributionRuleService {
    void create(BudgetDistributionRuleSaveRequest saveRequest, String email);

    void update(BudgetDistributionRuleUpdateRequest updateRequest, String email);

    Set<BudgetDistributionRuleResponse> getAll(String email);

    void delete(UUID id, String email);
}
