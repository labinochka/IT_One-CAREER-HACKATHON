package ru.itis.financeimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.itis.financeapi.api.BudgetDistributionRuleApi;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleSaveRequest;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleUpdateRequest;
import ru.itis.financeapi.dto.response.BudgetDistributionRuleResponse;
import ru.itis.financeimpl.service.BudgetDistributionRuleService;

import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BudgetDistributionRuleController implements BudgetDistributionRuleApi {
    private final BudgetDistributionRuleService service;

    @Override
    public void create(BudgetDistributionRuleSaveRequest saveRequest) {
        service.create(saveRequest);
    }

    @Override
    public void update(BudgetDistributionRuleUpdateRequest updateRequest) {
        service.update(updateRequest);
    }

    @Override
    public Set<BudgetDistributionRuleResponse> getAll() {
        return service.getAll();
    }

    @Override
    public void delete(UUID id) {
        service.delete(id);
    }
}
