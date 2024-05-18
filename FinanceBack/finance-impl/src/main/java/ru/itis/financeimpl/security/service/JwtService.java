package ru.itis.financeimpl.security.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.financeimpl.security.provider.JwtAccessTokenProvider;

import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate.before(new Date());
    }

    private <R> R extractClaim(String token, Function<Claims, R> claimsResolver) {
        Claims claims = jwtAccessTokenProvider.parseAccessToken(token);
        return claimsResolver.apply(claims);
    }

}
