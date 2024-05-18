package ru.itis.financeimpl.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.api.SubscriptionApi;
import ru.itis.financeapi.dto.request.SubscriptionSaveRequest;
import ru.itis.financeapi.dto.request.SubscriptionUpdateRequest;
import ru.itis.financeapi.dto.response.SubscriptionResponse;

import java.util.List;
import java.util.UUID;

@RestController
public class SubscriptionController implements SubscriptionApi {

    @Override
    public void add(SubscriptionSaveRequest subscriptionRequest) {

    }

    @Override
    public List<SubscriptionResponse> getAllByAccountId(UUID accountId) {
        return null;
    }

    @Override
    public void update(UUID id, SubscriptionUpdateRequest subscriptionUpdateRequest) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
