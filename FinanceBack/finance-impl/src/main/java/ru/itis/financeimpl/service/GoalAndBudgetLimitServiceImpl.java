package ru.itis.financeimpl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;
import ru.itis.financeimpl.mapper.GoalAndBudgetLimitMapper;
import ru.itis.financeimpl.repository.GoalAndBudgetLimitRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoalAndBudgetLimitServiceImpl implements GoalAndBudgetLimitService {
    private final GoalAndBudgetLimitRepository repository;
    private final GoalAndBudgetLimitMapper mapper;

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
        return Set.of();
    }

    @Override
    public void update(GoalAndBudgetLimitUpdateRequest updateRequest) {
        if (repository.findById(updateRequest.goalAndLimitId()).isEmpty()){
            throw new IllegalArgumentException();
        }
        repository.save(mapper.fromRequest(updateRequest));
    }

    @Override
    public void delete(UUID id) {
        if (repository.findById(id).isEmpty()){
            throw new IllegalArgumentException();
        }
        repository.deleteById(id);
    }
}
