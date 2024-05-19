package ru.itis.financeimpl.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itis.financeapi.dto.response.BudgetDistributionRuleResponse;
import ru.itis.financeimpl.mapper.BudgetDistributionRuleMapper;
import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.model.BudgetDistributionRule;
import ru.itis.financeimpl.repository.AccountRepository;
import ru.itis.financeimpl.repository.BudgetDistributionRuleRepository;
import ru.itis.financeimpl.service.impl.BudgetDistributionRuleServiceImpl;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class BudgetDistributionRuleServiceImplTest {

    @Mock
    private BudgetDistributionRuleRepository distributionRuleRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BudgetDistributionRuleMapper mapper;

    @InjectMocks
    private BudgetDistributionRuleServiceImpl service;

    @BeforeEach
    public void setup() {
        service = new BudgetDistributionRuleServiceImpl(distributionRuleRepository, accountRepository, mapper);
    }

    @Test
    public void shouldReturnAll() {
        String email = "mm@mail.ru";
        Set<BudgetDistributionRuleResponse> rules = getRules();
        Mockito.when(service.getAll(email)).thenReturn(rules);

        Set<BudgetDistributionRuleResponse> responses = service.getAll(email);

        Assertions.assertNotNull(responses);
        Assertions.assertEquals(rules.size(), responses.size());
    }
    private Set<BudgetDistributionRuleResponse> getRules() {
        Account account = new Account();
        account.setEmail("mm@mail.ru");
        BudgetDistributionRule rule1 = new BudgetDistributionRule(UUID.randomUUID(), Instant.now(), Instant.now(),
                account, 100000, 500, "продукты");
        BudgetDistributionRule rule2 = new BudgetDistributionRule(UUID.randomUUID(), Instant.now(), Instant.now(),
                account, 100000, 500, "продукты");
        return Set.of(mapper.toResponse(rule1), mapper.toResponse(rule2));
    }
}
