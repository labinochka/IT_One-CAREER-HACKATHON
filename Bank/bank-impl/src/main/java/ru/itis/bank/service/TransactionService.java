package ru.itis.bank.service;

import ru.itis.bank.dto.request.TransactionGetRequest;
import ru.itis.bank.dto.response.TransactionResponse;

import java.util.Set;

public interface TransactionService {
    Set<TransactionResponse> getTransactionsByMail(TransactionGetRequest request);
}
