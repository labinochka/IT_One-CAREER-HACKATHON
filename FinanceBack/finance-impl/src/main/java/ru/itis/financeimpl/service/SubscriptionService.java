package ru.itis.financeimpl.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.SubscriptionSaveRequest;
import ru.itis.financeapi.dto.request.SubscriptionUpdateRequest;
import ru.itis.financeapi.dto.response.SubscriptionResponse;
import ru.itis.financeimpl.mapper.SubscriptionMapper;
import ru.itis.financeimpl.model.Subscription;
import ru.itis.financeimpl.repository.SubscriptionRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public void save(SubscriptionSaveRequest subscriptionRequest) {
        subscriptionRepository.save(subscriptionMapper.toEntity(subscriptionRequest));
    }

    public List<SubscriptionResponse> findAllByAccountId(UUID accountId) {
        return subscriptionRepository.findAllByAccountId(accountId)
                .stream()
                .map(subscriptionMapper::toResponse)
                .toList();
    }


    public void update(UUID id, SubscriptionUpdateRequest subscriptionUpdateRequest) {
        Subscription subscription = subscriptionMapper.toEntity(subscriptionUpdateRequest);
        subscription.setId(id);
        subscriptionRepository.save(subscription);
    }

    public void deleteById(UUID id) {
        subscriptionRepository.deleteById(id);
    }
}
