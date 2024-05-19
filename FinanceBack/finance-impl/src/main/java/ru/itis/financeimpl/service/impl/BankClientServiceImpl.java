package ru.itis.financeimpl.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;
import ru.itis.financeimpl.service.BankClientService;
import ru.itis.financeimpl.service.TransactionService;
import ru.itis.financeimpl.util.BankClientConstants;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class BankClientServiceImpl implements BankClientService {
    private final TransactionService transactionService;
    private final ObjectMapper mapper;

    @Override
    public void synchronize(String email) {
        Page<TransactionResponse> transactionResponses = transactionService.getAll(1, Integer.MAX_VALUE, email);
        Instant startDate = transactionResponses.stream()
                .max(Comparator.comparing(TransactionResponse::date))
                .get()
                .date();
        fetchDataFromExternalApi(email, startDate, Instant.now());
    }

    private void fetchDataFromExternalApi(String email, Instant startDate, Instant endDate) {
        RestTemplate restTemplate = new RestTemplate();
        String resultJson = restTemplate.getForObject(
                BankClientConstants.GET_TRANSACTION.formatted(email, startDate, endDate),
                String.class
        );
        HashSet response;
        try {
            response = mapper.readValue(resultJson, HashSet.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
        if (response != null) {
            LocalTransactionDto request;
            for (Object dto : response) {
                request = (LocalTransactionDto) dto;
                transactionService.create(
                        new TransactionRequest(request.date, request.amount, request.category, request.transactionalType),
                        email
                );
            }
        }
    }

    private record LocalTransactionDto(Instant date,
                                       int amount,
                                       String transactionalType,
                                       String category) {
    }
}
