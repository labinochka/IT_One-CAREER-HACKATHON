package ru.itis.financeimpl.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.api.AccountApi;
import ru.itis.financeapi.dto.request.AccountSaveRequest;
import ru.itis.financeapi.dto.request.AccountUpdateRequest;
import ru.itis.financeapi.dto.response.AccountResponse;

import java.util.UUID;

@RestController
public class AccountController implements AccountApi {

    @Override
    public void create(AccountSaveRequest accountSaveRequest) {

    }

    @Override
    public AccountResponse findByEmail(String username) {
        return null;
    }

    @Override
    public void update(UUID id, AccountUpdateRequest accountUpdateRequest) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
