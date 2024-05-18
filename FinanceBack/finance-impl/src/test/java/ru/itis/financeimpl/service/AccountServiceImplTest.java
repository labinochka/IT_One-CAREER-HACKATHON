package ru.itis.financeimpl.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.financeapi.dto.response.AccountResponse;
import ru.itis.financeimpl.mapper.AccountMapperImpl;
import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.repository.AccountRepository;
import ru.itis.financeimpl.service.impl.AccountServiceImpl;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapperImpl mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountServiceImpl service;

    @BeforeEach
    public void setup() {
        service = new AccountServiceImpl(repository, mapper, passwordEncoder);
    }

    @Test
    public void shouldReturnByEmail() {
        String email = "marina@mail.ru";
        List<AccountResponse> accounts = getAccounts();

        Mockito.when(service.findAll()).thenReturn(accounts);
        AccountResponse response = service.findByEmail(email);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(accounts.get(1), response);

    }

    private List<AccountResponse> getAccounts() {
        Account account1 = new Account(UUID.randomUUID(), "lab@mail.ru", "password", "Marina",
                "Labenskaya", null, null, null, null);

        Account account2 = new Account(UUID.randomUUID(), "marina@mail.ru", "password", "Dima",
                "Labenskiy", null, null, null, null);

        return List.of(mapper.toResponse(account1), mapper.toResponse(account2));
    }
}
