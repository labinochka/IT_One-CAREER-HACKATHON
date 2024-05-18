package ru.itis.financeimpl.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.api.TransactionApi;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;

import java.time.Instant;
import java.util.UUID;

@RestController
public class TransactionController implements TransactionApi {

    @Override
    public Page<TransactionResponse> getAll(int offset, int limit) {
        return null;
    }

    @Override
    public Page<TransactionResponse> getByCategory(int offset, int limit, String category) {
        return null;
    }

    @Override
    public Page<TransactionResponse> getByDate(int offset, int limit, Instant date) {
        return null;
    }

    @Override
    public Page<TransactionResponse> getByMonth(int offset, int limit, String month, int year) {
        return null;
    }

    @Override
    public Page<TransactionResponse> getByYear(int offset, int limit, int year) {
        return null;
    }

    @Override
    public Page<TransactionResponse> getByTransactionalType(int offset, int limit, String type) {
        return null;
    }

    @Override
    public UUID create(TransactionRequest request) {
        return null;
    }

    @Override
    public void updateById(UUID id, TransactionRequest request) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
