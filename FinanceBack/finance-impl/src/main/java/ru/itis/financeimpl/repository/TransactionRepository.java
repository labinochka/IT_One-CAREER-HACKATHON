package ru.itis.financeimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.financeimpl.model.Transaction;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<UUID, Transaction> {
}
