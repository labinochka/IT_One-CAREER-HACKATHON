package ru.itis.financeimpl.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.AuthRequest;
import ru.itis.financeapi.dto.response.AuthResponse;
import ru.itis.financeimpl.security.provider.JwtAccessTokenProvider;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationProvider authenticationProvider;

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    public AuthResponse authenticate(AuthRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken tokenToAuthenticate = UsernamePasswordAuthenticationToken.unauthenticated(
            authenticationRequest.email(),
            authenticationRequest.password()
        );
        log.info("authenticate: {}", authenticationRequest.password());
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(tokenToAuthenticate);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid login or password");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtAccessTokenProvider.createAccessToken(
                authentication.getName(),
                Map.of()
        );
        String refreshToken = null;
        return new AuthResponse(accessToken, refreshToken);
    }

}
