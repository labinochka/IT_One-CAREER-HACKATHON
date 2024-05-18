package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.AccountSaveRequest;
import ru.itis.financeapi.dto.response.AccountResponse;
import ru.itis.financeimpl.exception.AccountNotFoundException;
import ru.itis.financeimpl.mapper.AccountMapper;
import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.model.Role;
import ru.itis.financeimpl.repository.AccountRepository;
import ru.itis.financeimpl.service.AccountService;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(AccountSaveRequest saveRequest) {
        Account account = accountMapper.toEntity(saveRequest);
        account.setRole(Role.USER);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public AccountResponse findByEmail(String email) {
        return accountRepository.findByEmail(email)
                .map(accountMapper::toResponse)
                .orElseThrow(() -> new AccountNotFoundException(email)
        );
    }
}
