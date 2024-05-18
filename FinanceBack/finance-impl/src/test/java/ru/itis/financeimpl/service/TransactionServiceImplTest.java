package ru.itis.financeimpl.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;
import ru.itis.financeimpl.mapper.TransactionMapper;
import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.model.Transaction;
import ru.itis.financeimpl.repository.AccountRepository;
import ru.itis.financeimpl.repository.TransactionRepository;
import ru.itis.financeimpl.service.impl.TransactionServiceImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionMapper mapper;

    private final String email = "test@gmail.com";

    @InjectMocks
    private TransactionServiceImpl service;

    @BeforeEach
    public void setup() {
        service = new TransactionServiceImpl(transactionRepository, accountRepository, mapper);
    }

    @Test
    public void shouldReturnAllTransactions() {
        Page<TransactionResponse> transactions = getTransactions();
        Mockito.when(service.getAll(0, 10, "test@gmail.com")).thenReturn(transactions);

        Page<TransactionResponse> responses = service.getAll(0, 10, "test@gmail.com");
        Assertions.assertNotNull(responses);
        Assertions.assertEquals(transactions.getSize(), responses.getSize());
    }

    @Test
    public void shouldReturnByCategory() {
        Page<TransactionResponse> transactions = getTransactions();
        String category = "продукты";

        Mockito.when(service.getAll(0, 10, email)).thenReturn(transactions);
        Page<TransactionResponse> responses = service.getByCategory(0, 10, category, email);

        Assertions.assertNotNull(responses);
        Assertions.assertEquals(1, responses.getTotalElements());
    }

    @Test
    public void shouldReturnByTransactionalType() {
        Page<TransactionResponse> transactions = getTransactions();
        String type = "пополнение";

        Mockito.when(service.getAll(0, 10, email)).thenReturn(transactions);
        Page<TransactionResponse> responses = service.getByTransactionalType(0, 10, type, email);

        Assertions.assertNotNull(responses);
        Assertions.assertEquals(1, responses.getTotalElements());
    }

    @Test
    public void shouldReturnUuid() {
        TransactionRequest transaction = new TransactionRequest(Instant.now(), 1000, "продукты",
                "расход");
        UUID response = service.create(transaction, email);
        Assertions.assertNotNull(response);
    }

    private Page<TransactionResponse> getTransactions() {
        Transaction transaction1 = new Transaction(UUID.randomUUID(), Instant.now(), Instant.now(), new Account(),
                Instant.now(), 1000, "продукты", "расход", true);

        Transaction transaction2 = new Transaction(UUID.randomUUID(), Instant.now(), Instant.now(), new Account(),
                Instant.now(), 50000, "зарплата", "пополнение", true);

        List<TransactionResponse> data = new ArrayList<>();
        data.add(mapper.toResponse(transaction1));
        data.add(mapper.toResponse(transaction2));

        return new PageImpl<>(data);
    }
}
