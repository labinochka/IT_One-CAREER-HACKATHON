package ru.itis.financeimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Page<TransactionResponse> getAll(int offset, int limit, UserDetails userDetails) {
        return service.getAll(offset, limit, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByCategory(int offset, int limit, String category, UserDetails userDetails) {
        return service.getByCategory(offset, limit, category, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByDate(int offset, int limit, Instant date, UserDetails userDetails) {
        return service.getByDate(offset, limit, date, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByMonth(int offset, int limit, int month, int year, UserDetails userDetails) {
        return service.getByMonth(offset, limit, month, year, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByYear(int offset, int limit, int year, UserDetails userDetails) {
        return service.getByYear(offset, limit, year, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByTransactionalType(int offset, int limit, String type, UserDetails userDetails) {
        return service.getByTransactionalType(offset, limit, type, userDetails.getUsername());
    }

    @Override
    public Set<TransactionResponse> getByDates(Instant startDate, Instant endDate, UserDetails userDetails) {
        return null;
    }

    @Override
    public UUID create(TransactionRequest request, UserDetails userDetails) {
        return service.create(request, userDetails.getUsername());
    }

    @Override
    public void updateById(UUID id, TransactionRequest request, UserDetails userDetails) {
        service.updateById(id, request, userDetails.getUsername());
    }

    @Override
    public void deleteById(UUID id, UserDetails userDetails) {
        service.deleteById(id, userDetails.getUsername());
    }
}
