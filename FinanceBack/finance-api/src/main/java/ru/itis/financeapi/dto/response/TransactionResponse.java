package ru.itis.financeapi.dto.response;

import java.time.Instant;
import java.util.UUID;

public record TransactionResponse(
        UUID accountId,

        Instant date,

        int amount,

        String category,

        String transactionalType,

        boolean isEditable
) {
}
