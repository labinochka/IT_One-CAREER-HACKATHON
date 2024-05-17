package ru.itis.financeapi.dto.response;

import java.util.UUID;

public record AccountResponse(
        UUID id,
        String email,
        String firstName,
        String lastName
) {
}
