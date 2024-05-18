package ru.itis.financeapi.dto.request;

public record AuthRequest(
        String email,
        String password) {
}
