package ru.itis.financeimpl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;
import ru.itis.financeimpl.repository.GoalAndBudgetLimitRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GoalAndBudgetLimitServiceImpl implements GoalAndBudgetLimitService {
    private final GoalAndBudgetLimitRepository repository;

    @Override
    public void create(GoalAndBudgetLimitCreateRequest saveRequest) {

    }

    @Override
    public Set<GoalAndBudgetLimitResponse> getAllCurrent() {
        return Set.of();
    }

    @Override
    public Set<GoalAndBudgetLimitWithProgressResponse> getAllCurrentWithProgress() {
        return Set.of();
    }

    @Override
    public void update(GoalAndBudgetLimitUpdateRequest updateRequest) {

    }

    @Override
    public void delete(String username) {

    }
}
