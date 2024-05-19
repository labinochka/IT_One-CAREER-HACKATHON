package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.financeapi.dto.response.TransactionResponse;
import ru.itis.financeimpl.service.TransactionService;
import ru.itis.financeimpl.util.BankClientConstants;

import java.time.Instant;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class BankClientService {
    private final TransactionService transactionService;

    public void synchronize(String email){
        Page<TransactionResponse> transactionResponses = transactionService.getAll(1, Integer.MAX_VALUE, email);
        Instant startDate = transactionResponses.stream()
                .max(Comparator.comparing(TransactionResponse::date))
                .get()
                .date();
        fetchDataFromExternalApi(startDate, Instant.now());
    }

    private void fetchDataFromExternalApi(Instant startDate, Instant endDate) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(
                BankClientConstants.EXTERNAL_API_URL.formatted(startDate, endDate),
                String.class
        );
        System.out.println(result);
    }
}
