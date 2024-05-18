package ru.itis.financeimpl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.financeimpl.model.Transaction;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Page<Transaction> findAllByAccount_Id(UUID account_id, Pageable pageable);

    Page<Transaction> findAllByCategoryAndAccount_Id(String category, Pageable pageable, UUID accountId);

    Page<Transaction> findAllByDateAndAccount_Id(Instant date, Pageable pageable, UUID accountId);

    @Query("SELECT t FROM Transaction t WHERE YEAR(t.date) = :year AND MONTH(t.date) = :month")
    Page<Transaction> findAllByMonthAndYearAndAccount_Id(@Param("year") int month,
                                                         @Param("month") int year,
                                                         Pageable pageable,
                                                         UUID accountId);

    @Query("SELECT t FROM Transaction t WHERE YEAR(t.date) = :yearh")
    Page<Transaction> findAllByYearAndAccount_Id(@Param("year") int year, Pageable pageable, UUID accountId);

    Page<Transaction> findAllByTransactionalTypeAndAccount_Id(String type, Pageable pageable, UUID accountId);

    List<Transaction> findAllByDateBetweenAndAccount_Id(Instant startDate, Instant endDate, UUID accountId);
}
