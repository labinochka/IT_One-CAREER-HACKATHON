package ru.itis.financeapi.dto.response;

public record AuthResponse(
        String accessToken,
        String refreshToken) {
}
