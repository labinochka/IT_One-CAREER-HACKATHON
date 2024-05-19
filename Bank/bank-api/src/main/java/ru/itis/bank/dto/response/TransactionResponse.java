package ru.itis.bank.dto.response;

import java.time.Instant;

public record TransactionResponse(
        Instant date,

        int amount,

        String transactionalType,

        String category
) {
}
