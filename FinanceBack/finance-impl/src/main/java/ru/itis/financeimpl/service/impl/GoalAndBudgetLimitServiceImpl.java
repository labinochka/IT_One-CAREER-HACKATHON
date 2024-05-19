package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;
import ru.itis.financeapi.dto.response.TransactionResponse;
import ru.itis.financeimpl.exception.AccountNotFoundException;
import ru.itis.financeimpl.exception.GoalAndBudgetLimitNotFoundException;
import ru.itis.financeimpl.exception.NoAccessException;
import ru.itis.financeimpl.mapper.GoalAndBudgetLimitMapper;
import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.model.GoalAndBudgetLimit;
import ru.itis.financeimpl.repository.AccountRepository;
import ru.itis.financeimpl.repository.GoalAndBudgetLimitRepository;
import ru.itis.financeimpl.service.GoalAndBudgetLimitService;
import ru.itis.financeimpl.service.TransactionService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GoalAndBudgetLimitServiceImpl implements GoalAndBudgetLimitService {
    private final GoalAndBudgetLimitRepository goalAndLimitRepository;
    private final GoalAndBudgetLimitMapper mapper;
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    @Override
    public void create(GoalAndBudgetLimitCreateRequest saveRequest, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));
        GoalAndBudgetLimit goalAndBudgetLimit = mapper.fromRequest(saveRequest);
        goalAndBudgetLimit.setAccount(account);
        goalAndLimitRepository.save(goalAndBudgetLimit);
    }

    @Override
    public Set<GoalAndBudgetLimitResponse> getAllCurrent(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));
        UUID accountId = account.getId();
        List<GoalAndBudgetLimit> list = goalAndLimitRepository.findAllByAccountId(accountId)
                .orElseThrow(GoalAndBudgetLimitNotFoundException::new);
        List<GoalAndBudgetLimitResponse> result = list.stream()
                .map(mapper::toResponse)
                .toList();
        return new HashSet<>(result);
    }

    @Override
    public Set<GoalAndBudgetLimitWithProgressResponse> getAllCurrentWithProgress(String email) {
        Set<GoalAndBudgetLimitResponse> goalsAndLimits = getAllCurrent(email);
        Set<GoalAndBudgetLimitWithProgressResponse> result = new HashSet<>();
        for (GoalAndBudgetLimitResponse currentGoalAndLimit : goalsAndLimits) {
            result.add(calculateProgress(currentGoalAndLimit, email));
        }
        return result;
    }

    private GoalAndBudgetLimitWithProgressResponse calculateProgress
            (GoalAndBudgetLimitResponse currentGoalAndLimit, String email) {
        int currentGoalMakeMoneyProgress = 0;
        int currentGoalSpendMoneyProgress = currentGoalAndLimit.budgetLimit();
        Set<TransactionResponse> transactions = transactionService.getByDates(
                currentGoalAndLimit.startDate(),
                currentGoalAndLimit.endDate(),
                email
        );
        for (TransactionResponse transaction : transactions) {
            if (transaction.category().equals("deposit")) {
                currentGoalMakeMoneyProgress += transaction.amount();
            } else if (transaction.category().equals("payment")) {
                currentGoalSpendMoneyProgress += transaction.amount();
            }
        }
        return new GoalAndBudgetLimitWithProgressResponse(
                currentGoalAndLimit.makeMoneyGoal(),
                currentGoalAndLimit.spendMoneyGoal(),
                currentGoalAndLimit.budgetLimit(),
                currentGoalAndLimit.startDate(),
                currentGoalAndLimit.endDate(),
                currentGoalMakeMoneyProgress,
                currentGoalSpendMoneyProgress
        );
    }


    @Override
    public void update(GoalAndBudgetLimitUpdateRequest updateRequest, String email) {
        GoalAndBudgetLimit goalAndBudgetLimit = goalAndLimitRepository.findById(updateRequest.goalAndLimitId())
                        .orElseThrow(GoalAndBudgetLimitNotFoundException::new);
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));
        UUID accountId = account.getId();
        if (!goalAndBudgetLimit.getAccount().getId().equals(accountId)){
            throw new NoAccessException();
        }
        GoalAndBudgetLimit result = mapper.fromRequest(updateRequest);
        result.setId(goalAndBudgetLimit.getId());
        goalAndLimitRepository.save(result);
    }

    @Override
    public void delete(UUID id, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));
        GoalAndBudgetLimit goalAndBudgetLimit = goalAndLimitRepository.findById(id)
                .orElseThrow(GoalAndBudgetLimitNotFoundException::new);
        if (!goalAndBudgetLimit.getAccount().getId().equals(account.getId())) {
            throw new NoAccessException();
        }
        goalAndLimitRepository.deleteById(id);
    }
}
