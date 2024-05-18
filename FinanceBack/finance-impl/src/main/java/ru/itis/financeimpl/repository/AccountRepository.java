package ru.itis.financeimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.financeapi.dto.response.AccountResponse;
import ru.itis.financeimpl.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);
}
