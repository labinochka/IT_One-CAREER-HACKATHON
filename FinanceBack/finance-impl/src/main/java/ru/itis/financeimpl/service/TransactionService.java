package ru.itis.financeimpl.service;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public interface TransactionService {

    Page<TransactionResponse> getAll(int offset, int limit, String username);

    Page<TransactionResponse> getByCategory(int offset, int limit, String category, String email);

    Page<TransactionResponse> getByDate(int offset, int limit, Instant date, String email);

    Page<TransactionResponse> getByMonth(int offset, int limit, int month, int year, String email);

    Page<TransactionResponse> getByYear(int offset, int limit, int year, String email);

    Page<TransactionResponse> getByTransactionalType(int offset, int limit, String transactionalType, String email);

    Set<TransactionResponse> getByDates(Instant startDate, Instant endDate, String email);

    UUID create(TransactionRequest request, String email);

    void updateById(UUID id, TransactionRequest request, String email);

    void deleteById(UUID id, String email);

}
