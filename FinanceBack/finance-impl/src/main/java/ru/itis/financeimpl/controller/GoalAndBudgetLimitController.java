package ru.itis.financeimpl.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.api.GoalAndBudgetLimitApi;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;

import java.util.Set;

@RestController
public class GoalAndBudgetLimitController implements GoalAndBudgetLimitApi {

    @Override
    public void create(GoalAndBudgetLimitCreateRequest saveRequest) {

    }

    @Override
    public Set<GoalAndBudgetLimitResponse> getAllCurrent() {
        return null;
    }

    @Override
    public Set<GoalAndBudgetLimitWithProgressResponse> getAllCurrentWithProgress() {
        return null;
    }

    @Override
    public void update(GoalAndBudgetLimitUpdateRequest updateRequest) {

    }

    @Override
    public void delete(String username) {

    }
}
