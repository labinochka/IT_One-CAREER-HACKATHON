package ru.itis.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.bank.model.Transaction;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<List<Transaction>>  findByMailAndDateBetween(String mail, Instant start, Instant end);
}
