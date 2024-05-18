package ru.itis.financeimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.api.SubscriptionApi;
import ru.itis.financeapi.dto.request.SubscriptionSaveRequest;
import ru.itis.financeapi.dto.request.SubscriptionUpdateRequest;
import ru.itis.financeapi.dto.response.SubscriptionResponse;
import ru.itis.financeimpl.service.SubscriptionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SubscriptionController implements SubscriptionApi {

    private final SubscriptionService subscriptionService;

    @Override
    public void add(SubscriptionSaveRequest subscriptionRequest) {
        subscriptionService.save(subscriptionRequest);
        
    @Override
    public List<SubscriptionResponse> getAllByAccountId(UUID accountId) {
        return subscriptionService.findAllByAccountId(accountId);
    }

    @Override
    public void update(UUID id, SubscriptionUpdateRequest subscriptionUpdateRequest) {
        subscriptionService.update(id, subscriptionUpdateRequest);
    }

    @Override
    public void deleteById(UUID id) {
        subscriptionService.deleteById(id);
    }
}
