package ru.itis.financeimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.api.TransactionApi;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;
import ru.itis.financeimpl.service.TransactionService;
import ru.itis.financeimpl.service.impl.BankClientServiceImpl;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TransactionController implements TransactionApi {

    private final TransactionService transactionService;
    private final BankClientServiceImpl bankClientService;
    @Override
    public Page<TransactionResponse> getAll(int offset, int limit, UserDetails userDetails) {
        return transactionService.getAll(offset, limit, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByCategory(int offset, int limit, String category, UserDetails userDetails) {
        return transactionService.getByCategory(offset, limit, category, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByDate(int offset, int limit, Instant date, UserDetails userDetails) {
        return transactionService.getByDate(offset, limit, date, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByMonth(int offset, int limit, int month, int year, UserDetails userDetails) {
        return transactionService.getByMonth(offset, limit, month, year, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByYear(int offset, int limit, int year, UserDetails userDetails) {
        return transactionService.getByYear(offset, limit, year, userDetails.getUsername());
    }

    @Override
    public Page<TransactionResponse> getByTransactionalType(int offset, int limit, String type, UserDetails userDetails) {
        return transactionService.getByTransactionalType(offset, limit, type, userDetails.getUsername());
    }

    @Override
    public Set<TransactionResponse> getByDates(Instant startDate, Instant endDate, UserDetails userDetails) {
        return null;
    }

    @Override
    public UUID create(TransactionRequest request, UserDetails userDetails) {
        return transactionService.create(request, userDetails.getUsername());
    }

    @Override
    public void updateById(UUID id, TransactionRequest request, UserDetails userDetails) {
        transactionService.updateById(id, request, userDetails.getUsername());
    }

    @Override
    public void deleteById(UUID id, UserDetails userDetails) {
        transactionService.deleteById(id, userDetails.getUsername());
    }

    @Override
    public void synchronizeWithBank(UserDetails userDetails) {
        bankClientService.synchronize(userDetails.getUsername());
    }
}
