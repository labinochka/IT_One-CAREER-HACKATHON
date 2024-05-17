package ru.itis.financeapi.dto.request;

public record AccountSaveRequest(
        String first_name,
        String last_name,
        String email,
        String password
) {
}
