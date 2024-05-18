package ru.itis.financeimpl.service;

import ru.itis.financeapi.dto.request.SubscriptionSaveRequest;
import ru.itis.financeapi.dto.request.SubscriptionUpdateRequest;
import ru.itis.financeapi.dto.response.SubscriptionResponse;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    void save(SubscriptionSaveRequest subscriptionRequest);

    List<SubscriptionResponse> findAllByAccountId(UUID accountId);


    void update(UUID id, SubscriptionUpdateRequest subscriptionUpdateRequest);

    void deleteById(UUID id);
}
