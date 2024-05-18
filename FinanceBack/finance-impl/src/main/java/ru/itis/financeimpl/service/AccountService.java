package ru.itis.financeimpl.service;

import ru.itis.financeapi.dto.response.AccountResponse;

public interface AccountService {

    AccountResponse findByEmail(String email);
}
