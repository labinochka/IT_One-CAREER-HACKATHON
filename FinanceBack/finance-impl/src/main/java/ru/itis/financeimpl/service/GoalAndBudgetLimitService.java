package ru.itis.financeimpl.service;

import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;

import java.util.Set;
import java.util.UUID;

public interface GoalAndBudgetLimitService {
    void create(GoalAndBudgetLimitCreateRequest saveRequest, String email);

    Set<GoalAndBudgetLimitResponse> getAllCurrent(String email);

    Set<GoalAndBudgetLimitWithProgressResponse> getAllCurrentWithProgress(String email);

    void update(GoalAndBudgetLimitUpdateRequest updateRequest, String email);

    void delete(UUID id, String email);
}
