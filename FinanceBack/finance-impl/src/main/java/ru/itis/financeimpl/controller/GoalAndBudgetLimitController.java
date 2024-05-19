package ru.itis.financeimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
public class GoalAndBudgetLimitController implements GoalAndBudgetLimitApi {
    private final GoalAndBudgetLimitService service;

    @Override
    public void create(GoalAndBudgetLimitCreateRequest saveRequest, UserDetails userDetails) {
        service.create(saveRequest, userDetails.getUsername());
    }

    @Override
    public Set<GoalAndBudgetLimitResponse> getAllCurrent(UserDetails userDetails) {
        return service.getAllCurrent(userDetails.getUsername());
    }

    @Override
    public Set<GoalAndBudgetLimitWithProgressResponse> getAllCurrentWithProgress(UserDetails userDetails) {
        return service.getAllCurrentWithProgress(userDetails.getUsername());
    }

    @Override
    public void update(GoalAndBudgetLimitUpdateRequest updateRequest, UserDetails userDetails) {
        service.update(updateRequest, userDetails.getUsername());
    }

    @Override
    public void delete(UUID id, UserDetails userDetails) {
        service.delete(id, userDetails.getUsername());
    }
}
