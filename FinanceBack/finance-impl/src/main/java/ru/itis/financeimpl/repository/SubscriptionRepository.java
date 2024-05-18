package ru.itis.financeimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.financeimpl.model.Subscription;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    List<Subscription> findAllByAccountId(UUID accountId);
}
