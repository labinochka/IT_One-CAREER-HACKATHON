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

    Page<Transaction> findAllByAccountId(UUID account_id, Pageable pageable);

    Page<Transaction> findAllByCategoryAndAccountId(String category, Pageable pageable, UUID accountId);

    Page<Transaction> findAllByDateAndAccountId(Instant date, Pageable pageable, UUID accountId);

<<<<<<< HEAD
    @Query("SELECT t FROM Transaction t WHERE YEAR(t.date) = :year AND MONTH(t.date) = :month AND " +
            "t.account.id = :accountId")
    Page<Transaction> findAllByMonthAndYearAndAccountId(@Param("year") int month,
=======
    @Query("SELECT t FROM Transaction t " +
            "WHERE YEAR(t.date) = :year AND MONTH(t.date) = :month AND t.account.id = :accountId")
    Page<Transaction> findAllByMonthAndYearAndAccount_Id(@Param("year") int month,
>>>>>>> 5ed848aeee15f45158ce392913b25b6ee00dadd7
                                                         @Param("month") int year,
                                                         Pageable pageable,
                                                         @Param("accountId") UUID accountId);

    @Query("SELECT t FROM Transaction t WHERE YEAR(t.date) = :yearh AND t.account.id = :accountId")
<<<<<<< HEAD
    Page<Transaction> findAllByYearAndAccountId(@Param("year") int year, Pageable pageable, UUID accountId);
=======
    Page<Transaction> findAllByYearAndAccount_Id(@Param("year") int year,
                                                 Pageable pageable,
                                                 @Param("accountId") UUID accountId);
>>>>>>> 5ed848aeee15f45158ce392913b25b6ee00dadd7

    Page<Transaction> findAllByTransactionalTypeAndAccountId(String type, Pageable pageable, UUID accountId);

    List<Transaction> findAllByDateBetweenAndAccountId(Instant startDate, Instant endDate, UUID accountId);
}
