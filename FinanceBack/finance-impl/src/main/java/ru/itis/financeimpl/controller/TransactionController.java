package ru.itis.financeimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.api.TransactionApi;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;
import ru.itis.financeimpl.service.TransactionService;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionApi {

    private final TransactionService service;

    @Override
    public Page<TransactionResponse> getAll(int offset, int limit) {
        return service.getAll(offset, limit);
    }

    @Override
    public Page<TransactionResponse> getByCategory(int offset, int limit, String category) {
        return service.getByCategory(offset, limit, category);
    }

    @Override
    public Page<TransactionResponse> getByDate(int offset, int limit, Instant date) {
        return service.getByDate(offset, limit, date);
    }

    @Override
    public Page<TransactionResponse> getByMonth(int offset, int limit, int month, int year) {
        return service.getByMonth(offset, limit, month, year);
    }

    @Override
    public Page<TransactionResponse> getByYear(int offset, int limit, int year) {
        return service.getByYear(offset, limit, year);
    }

    @Override
    public Page<TransactionResponse> getByTransactionalType(int offset, int limit, String type) {
        return service.getByTransactionalType(offset, limit, type);
    }

    @Override
    public Set<TransactionResponse> getByDates(Instant startDate, Instant endDate) {
        return null;
    }

    @Override
    public UUID create(TransactionRequest request) {
        return service.create(request);
    }

    @Override
    public void updateById(UUID id, TransactionRequest request) {
        service.updateById(id, request);
    }

    @Override
    public void deleteById(UUID id) {
        service.deleteById(id);
    }
}
