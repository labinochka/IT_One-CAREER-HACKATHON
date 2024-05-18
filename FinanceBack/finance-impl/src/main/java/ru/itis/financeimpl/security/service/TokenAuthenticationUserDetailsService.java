package ru.itis.financeimpl.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.response.AccountResponse;
import ru.itis.financeimpl.exception.AccountNotFoundException;
import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.repository.AccountRepository;
import ru.itis.financeimpl.security.userdetails.UserDetailsImpl;
import ru.itis.financeimpl.service.AccountService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenAuthenticationUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);
        Account account = accountRepository.findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Account with email: %s not found".formatted(username))
                );
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRole().name()));
        return UserDetailsImpl.builder()
                .id(account.getId())
                .username(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .password(account.getPassword())
                .authorities(authorities)
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .build();
    }

}
