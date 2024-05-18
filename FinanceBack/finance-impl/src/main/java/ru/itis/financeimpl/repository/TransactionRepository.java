package ru.itis.financeimpl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.financeimpl.model.Transaction;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Page<Transaction> findAllByCategory(String category, Pageable pageable);

    Page<Transaction> findAllByDate(Instant date, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE YEAR(t.date) = :year AND MONTH(t.date) = :month")
    Page<Transaction> findAllByMonthAndYear(@Param("year") int month, @Param("month") int year, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE YEAR(t.date) = :yearh")
    Page<Transaction> findAllByYear(@Param("month") int year, Pageable pageable);

    Page<Transaction> findAllByTransactionalType(String type, Pageable pageable);
}
