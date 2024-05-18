package ru.itis.financeimpl.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;
import ru.itis.financeimpl.model.GoalAndBudgetLimit;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface GoalAndBudgetLimitMapper {
    GoalAndBudgetLimit fromRequest(GoalAndBudgetLimitCreateRequest request);

    GoalAndBudgetLimit fromRequest(GoalAndBudgetLimitUpdateRequest request);

    GoalAndBudgetLimitResponse toResponse(GoalAndBudgetLimit goalAndBudgetLimit);

    GoalAndBudgetLimitWithProgressResponse toResponseWithProgress(GoalAndBudgetLimit goalAndBudgetLimit);
}
