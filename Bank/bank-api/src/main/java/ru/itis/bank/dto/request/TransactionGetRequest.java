package ru.itis.bank.dto.request;

import java.time.Instant;

public record TransactionGetRequest(
        String userMail,
        Instant startDate,
        Instant endDate
) {
}
