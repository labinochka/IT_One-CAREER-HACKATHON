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

    Page<TransactionResponse> getAll(int offset, int limit);

    Page<TransactionResponse> getByCategory(int offset, int limit, String category);

    Page<TransactionResponse> getByDate(int offset, int limit, Instant date);

    Page<TransactionResponse> getByMonth(int offset, int limit, int month, int year);

    Page<TransactionResponse> getByYear(int offset, int limit, int year);

    Page<TransactionResponse> getByTransactionalType(int offset, int limit, String transactionalType);

    Set<TransactionResponse> getByDates(Instant startDate, Instant endDate);

    UUID create(TransactionRequest request);

    void updateById(UUID id, TransactionRequest request);

    void deleteById(UUID id);

}
