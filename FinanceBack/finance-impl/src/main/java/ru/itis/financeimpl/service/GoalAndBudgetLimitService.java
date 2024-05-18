package ru.itis.financeimpl.service;

import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;

import java.util.Set;

public interface GoalAndBudgetLimitService {
    void create(GoalAndBudgetLimitCreateRequest saveRequest);

    Set<GoalAndBudgetLimitResponse> getAllCurrent();

    Set<GoalAndBudgetLimitWithProgressResponse> getAllCurrentWithProgress();

    void update(GoalAndBudgetLimitUpdateRequest updateRequest);

    void delete(String username);
}
