package ru.itis.financeimpl.service;

import ru.itis.financeapi.dto.request.AccountSaveRequest;
import ru.itis.financeapi.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {
    void create(AccountSaveRequest saveRequest);

    AccountResponse findByEmail(String email);

    List<AccountResponse> findAll();
}
