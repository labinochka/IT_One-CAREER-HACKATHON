package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleSaveRequest;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleUpdateRequest;
import ru.itis.financeapi.dto.response.BudgetDistributionRuleResponse;
import ru.itis.financeimpl.mapper.BudgetDistributionRuleMapper;
import ru.itis.financeimpl.repository.BudgetDistributionRuleRepository;
import ru.itis.financeimpl.service.BudgetDistributionRuleService;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BudgetDistributionRuleServiceImpl implements BudgetDistributionRuleService {
    private final BudgetDistributionRuleRepository repository;
    private final BudgetDistributionRuleMapper mapper;

    @Override
    public void create(BudgetDistributionRuleSaveRequest saveRequest) {
        repository.save(mapper.fromRequest(saveRequest));
    }

    @Override
    public void update(BudgetDistributionRuleUpdateRequest updateRequest) {
        repository.save(mapper.fromRequest(updateRequest));
    }

    @Override
    public Set<BudgetDistributionRuleResponse> getAll() {
        return Set.of();
    }

    @Override
    public void delete(UUID id) {

    }
}
