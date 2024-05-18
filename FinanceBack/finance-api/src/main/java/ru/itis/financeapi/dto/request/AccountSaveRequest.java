package ru.itis.financeapi.dto.request;

public record AccountSaveRequest(
        String firstName,
        String lastName,
        String email,
        String password) {
}
