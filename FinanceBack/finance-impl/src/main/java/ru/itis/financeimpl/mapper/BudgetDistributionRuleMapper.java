package ru.itis.financeimpl.mapper;

import org.mapstruct.Mapper;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleSaveRequest;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleUpdateRequest;
import ru.itis.financeapi.dto.response.BudgetDistributionRuleResponse;
import ru.itis.financeimpl.model.BudgetDistributionRule;

@Mapper(componentModel = "spring")
public interface BudgetDistributionRuleMapper {
    BudgetDistributionRule fromRequest(BudgetDistributionRuleSaveRequest saveRequest);

    BudgetDistributionRule fromRequest(BudgetDistributionRuleUpdateRequest updateRequest);

    BudgetDistributionRuleResponse toResponse(BudgetDistributionRule rule);
}
