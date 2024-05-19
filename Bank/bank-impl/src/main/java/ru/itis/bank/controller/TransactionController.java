package ru.itis.bank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.bank.api.TransactionApi;
import ru.itis.bank.dto.request.TransactionGetRequest;
import ru.itis.bank.dto.response.TransactionResponse;
import ru.itis.bank.service.TransactionService;

import java.time.Instant;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionApi {
    private final TransactionService service;

    @Override
    public Set<TransactionResponse> getTransactions(String mail, Instant startDate, Instant endDate) {
        return service.getTransactionsByMail(new TransactionGetRequest(mail, startDate, endDate));
    }
}
