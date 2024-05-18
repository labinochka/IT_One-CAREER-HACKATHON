package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleSaveRequest;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleUpdateRequest;
import ru.itis.financeapi.dto.response.BudgetDistributionRuleResponse;
import ru.itis.financeimpl.exception.AccountNotFoundException;
import ru.itis.financeimpl.exception.BudgetDistributionRuleException;
import ru.itis.financeimpl.exception.GoalAndBudgetLimitNotFoundException;
import ru.itis.financeimpl.exception.NoAccessException;
import ru.itis.financeimpl.mapper.BudgetDistributionRuleMapper;
import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.model.BudgetDistributionRule;
import ru.itis.financeimpl.model.GoalAndBudgetLimit;
import ru.itis.financeimpl.repository.AccountRepository;
import ru.itis.financeimpl.repository.BudgetDistributionRuleRepository;
import ru.itis.financeimpl.service.BudgetDistributionRuleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetDistributionRuleServiceImpl implements BudgetDistributionRuleService {
    private final BudgetDistributionRuleRepository ruleRepository;
    private final AccountRepository accountRepository;
    private final BudgetDistributionRuleMapper mapper;

    @Override
    public void create(BudgetDistributionRuleSaveRequest saveRequest, String email) {
        BudgetDistributionRule rule = mapper.fromRequest(saveRequest);
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));
        rule.setAccount(account);
        ruleRepository.save(rule);
    }

    @Override
    public void update(BudgetDistributionRuleUpdateRequest updateRequest, String email) {
        BudgetDistributionRule rule = ruleRepository.findById(updateRequest.ruleId())
                .orElseThrow(GoalAndBudgetLimitNotFoundException::new);
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));
        if (!rule.getAccount().getId().equals(account.getId())){
            throw new NoAccessException();
        }
        rule.setBudget(updateRequest.budget());
        rule.setCategory(updateRequest.category());
        ruleRepository.save(rule);
    }

    @Override
    public Set<BudgetDistributionRuleResponse> getAll(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));
        List<BudgetDistributionRule> rules = ruleRepository.findAllByAccountId(account.getId())
                .orElseThrow(BudgetDistributionRuleException::new);
        List<BudgetDistributionRuleResponse> result = rules.stream()
                .map(mapper::toResponse)
                .toList();
        return new HashSet<>(result);
    }

    @Override
    public void delete(UUID id, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));
        BudgetDistributionRule goalAndBudgetLimit = ruleRepository.findById(id)
                .orElseThrow(GoalAndBudgetLimitNotFoundException::new);
        if (!goalAndBudgetLimit.getAccount().getId().equals(account.getId())) {
            throw new NoAccessException();
        }
        ruleRepository.deleteById(id);
    }
}
