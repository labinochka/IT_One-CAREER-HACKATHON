package ru.itis.financeapi.dto.request;

import java.time.Instant;

public record TransactionRequest(
        Instant date,

        int amount,

        String category,

        String transactionalType
) {
}
