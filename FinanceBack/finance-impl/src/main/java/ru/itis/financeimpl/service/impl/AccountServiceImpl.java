package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.response.AccountResponse;
import ru.itis.financeimpl.exception.AccountNotFoundException;
import ru.itis.financeimpl.mapper.AccountMapper;
import ru.itis.financeimpl.repository.AccountRepository;
import ru.itis.financeimpl.service.AccountService;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse findByEmail(String email) {
        return accountRepository.findByEmail(email).map(accountMapper::toResponse).orElseThrow(
                () -> new AccountNotFoundException(email)
        );
    }
}
