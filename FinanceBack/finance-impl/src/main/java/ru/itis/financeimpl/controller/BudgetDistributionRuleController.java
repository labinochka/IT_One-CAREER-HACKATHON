package ru.itis.financeimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.itis.financeapi.api.BudgetDistributionRuleApi;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleSaveRequest;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleUpdateRequest;
import ru.itis.financeapi.dto.response.BudgetDistributionRuleResponse;
import ru.itis.financeimpl.service.BudgetDistributionRuleService;

import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class BudgetDistributionRuleController implements BudgetDistributionRuleApi {
    private final BudgetDistributionRuleService service;

    @Override
    public void create(BudgetDistributionRuleSaveRequest saveRequest, UserDetails userDetails) {
        service.create(saveRequest, userDetails.getUsername());
    }

    @Override
    public void update(BudgetDistributionRuleUpdateRequest updateRequest, UserDetails userDetails) {
        service.update(updateRequest, userDetails.getUsername());
    }

    @Override
    public Set<BudgetDistributionRuleResponse> getAll(UserDetails userDetails) {
        return service.getAll(userDetails.getUsername());
    }

    @Override
    public void delete(UUID id, UserDetails userDetails) {
        service.delete(id, userDetails.getUsername());
    }
}
