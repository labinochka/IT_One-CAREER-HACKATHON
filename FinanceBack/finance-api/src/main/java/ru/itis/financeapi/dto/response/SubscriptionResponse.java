package ru.itis.financeapi.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record SubscriptionResponse(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    Instant startDate,
    Instant endDate
) {
}
