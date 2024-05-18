package ru.itis.financeimpl.service;

import org.springframework.data.domain.Page;
import ru.itis.financeapi.dto.response.TransactionResponse;

public interface TransactionService {

    Page<TransactionResponse> getAll();
}
