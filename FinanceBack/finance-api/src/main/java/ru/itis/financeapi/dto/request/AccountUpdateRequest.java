package ru.itis.financeapi.dto.request;

public record AccountUpdateRequest(
        String firstName,
        String lastName,
        String email) {
}
