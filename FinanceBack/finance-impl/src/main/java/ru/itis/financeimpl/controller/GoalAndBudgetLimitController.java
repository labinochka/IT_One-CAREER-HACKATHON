package ru.itis.financeimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.api.GoalAndBudgetLimitApi;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;
import ru.itis.financeimpl.service.GoalAndBudgetLimitService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GoalAndBudgetLimitController implements GoalAndBudgetLimitApi {
    private final GoalAndBudgetLimitService service;

    @Override
    public void create(GoalAndBudgetLimitCreateRequest saveRequest) {
        service.create(saveRequest);
    }

    @Override
    public Set<GoalAndBudgetLimitResponse> getAllCurrent() {
        return service.getAllCurrent();
    }

    @Override
    public Set<GoalAndBudgetLimitWithProgressResponse> getAllCurrentWithProgress() {
        return service.getAllCurrentWithProgress();
    }

    @Override
    public void update(GoalAndBudgetLimitUpdateRequest updateRequest) {
        service.update(updateRequest);
    }

    @Override
    public void delete(UUID id) {
        service.delete(id);
    }
}
