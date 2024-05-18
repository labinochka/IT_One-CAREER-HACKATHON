package ru.itis.financeimpl.service;

import org.springframework.data.domain.Page;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;

import java.time.Instant;
import java.util.UUID;

public interface TransactionService {

    Page<TransactionResponse> getAll(int offset, int limit);

    Page<TransactionResponse> getByCategory(int offset, int limit, String category);

    Page<TransactionResponse> getByDate(int offset, int limit, Instant date);

    Page<TransactionResponse> getByMonth(int offset, int limit, int month, int year);

    Page<TransactionResponse> getByYear(int offset, int limit, int year);

    Page<TransactionResponse> getByTransactionalType(int offset, int limit, String transactionalType);

    UUID create(TransactionRequest request);

    void updateById(UUID id, TransactionRequest request);

    void deleteById(UUID id);

}
