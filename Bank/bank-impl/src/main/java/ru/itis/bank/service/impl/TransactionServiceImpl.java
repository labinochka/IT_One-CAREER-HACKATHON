package ru.itis.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.bank.dto.request.TransactionGetRequest;
import ru.itis.bank.dto.response.TransactionResponse;
import ru.itis.bank.model.Transaction;
import ru.itis.bank.repository.TransactionRepository;
import ru.itis.bank.service.TransactionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;

    @Override
    public Set<TransactionResponse> getTransactionsByMail(TransactionGetRequest request) {
        List<Transaction> transactions =
                repository.findByMailAndDateBetween(request.userMail(), request.startDate(), request.endDate())
                        .orElseThrow(IllegalArgumentException::new);
        List<TransactionResponse> result = transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getDate(),
                        transaction.getAmount(),
                        transaction.getTransactionalType(),
                        transaction.getCategory())
                ).toList();
        return new HashSet<>(result);
    }
}
