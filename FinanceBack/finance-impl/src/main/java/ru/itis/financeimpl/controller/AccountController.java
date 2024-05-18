package ru.itis.financeimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.api.AccountApi;
import ru.itis.financeapi.dto.request.AccountSaveRequest;
import ru.itis.financeapi.dto.request.AccountUpdateRequest;
import ru.itis.financeapi.dto.response.AccountResponse;
import ru.itis.financeimpl.service.AccountService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    private final AccountService accountService;

    @Override
    public void create(AccountSaveRequest accountSaveRequest) {

    }

    @Override
    public AccountResponse findByEmail(String email) {
        return accountService.findByEmail(email);
    }

    @Override
    public void update(UUID id, AccountUpdateRequest accountUpdateRequest) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
