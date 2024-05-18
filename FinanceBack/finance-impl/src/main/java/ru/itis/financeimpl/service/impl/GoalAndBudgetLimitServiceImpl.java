package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;
import ru.itis.financeapi.dto.response.TransactionResponse;
import ru.itis.financeimpl.exception.GoalAndBudgetLimitNotFoundException;
import ru.itis.financeimpl.mapper.GoalAndBudgetLimitMapper;
import ru.itis.financeimpl.repository.GoalAndBudgetLimitRepository;
import ru.itis.financeimpl.service.GoalAndBudgetLimitService;
import ru.itis.financeimpl.service.TransactionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoalAndBudgetLimitServiceImpl implements GoalAndBudgetLimitService {
    private final GoalAndBudgetLimitRepository repository;
    private final GoalAndBudgetLimitMapper mapper;
    private final TransactionService transactionService;

    @Override
    public void create(GoalAndBudgetLimitCreateRequest saveRequest) {
        repository.save(mapper.fromRequest(saveRequest));
    }

    @Override
    public Set<GoalAndBudgetLimitResponse> getAllCurrent() {
        List<GoalAndBudgetLimitResponse> list = repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
        return new HashSet<>(list);
    }

    @Override
    public Set<GoalAndBudgetLimitWithProgressResponse> getAllCurrentWithProgress() {
        List<GoalAndBudgetLimitResponse> goalsAndLimits = repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
        Set<GoalAndBudgetLimitWithProgressResponse> result = new HashSet<>();
        for (GoalAndBudgetLimitResponse currentGoalAndLimit : goalsAndLimits) {
            result.add(calculateProgress(currentGoalAndLimit));
        }
        return result;
    }

    private GoalAndBudgetLimitWithProgressResponse calculateProgress(GoalAndBudgetLimitResponse currentGoalAndLimit) {
        int currentGoalMakeMoneyProgress = 0;
        int currentGoalSpendMoneyProgress = currentGoalAndLimit.budgetLimit();
        Set<TransactionResponse> transactions = transactionService.getByDates(
                currentGoalAndLimit.startDate(),
                currentGoalAndLimit.endDate()
        );
        for (TransactionResponse transaction : transactions) {
            if (transaction.amount() > 0) {
                currentGoalMakeMoneyProgress += transaction.amount();
            } else if (transaction.amount() < 0) {
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
    public void update(GoalAndBudgetLimitUpdateRequest updateRequest) {
        if (repository.findById(updateRequest.goalAndLimitId()).isEmpty()) {
            throw new IllegalArgumentException();
        }
        repository.save(mapper.fromRequest(updateRequest));
    }

    @Override
    public void delete(UUID id) {
        if (repository.findById(id).isEmpty()) {
            throw new GoalAndBudgetLimitNotFoundException();
        }
        repository.deleteById(id);
    }
}
