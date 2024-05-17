package ru.itis.financeapi.dto.request;

import java.math.BigDecimal;
import java.time.Instant;

public record SubscriptionSaveRequest(
        String name,
        String description,
        BigDecimal price,
        Instant startDate,
        Instant endDate
) {
}
