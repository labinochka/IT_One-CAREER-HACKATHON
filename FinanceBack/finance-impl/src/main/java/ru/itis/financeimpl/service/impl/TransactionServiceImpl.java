package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;
import ru.itis.financeimpl.exception.AccountNotFoundException;
import ru.itis.financeimpl.exception.NoAccessException;
import ru.itis.financeimpl.exception.TransactionNotFoundException;
import ru.itis.financeimpl.mapper.TransactionMapper;
import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.model.Transaction;
import ru.itis.financeimpl.repository.AccountRepository;
import ru.itis.financeimpl.repository.TransactionRepository;
import ru.itis.financeimpl.service.TransactionService;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    private final TransactionMapper mapper;

    @Override
    public Page<TransactionResponse> getAll(int offset, int limit, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new AccountNotFoundException(email));
        UUID accountId = account.getId();
        return mapper.toResponse(
                transactionRepository.findAllByAccount_Id(
                        accountId,
                        PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "date"))
                )
        );
    }

    @Override
    public Page<TransactionResponse> getByCategory(int offset, int limit, String category, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new AccountNotFoundException(email));
        UUID accountId = account.getId();
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(transactionRepository.findAllByCategoryAndAccount_Id(category, pageable, accountId));
    }

    @Override
    public Page<TransactionResponse> getByDate(int offset, int limit, Instant date, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new AccountNotFoundException(email));
        UUID accountId = account.getId();
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(transactionRepository.findAllByDateAndAccount_Id(date, pageable, accountId));
    }

    @Override
    public Page<TransactionResponse> getByMonth(int offset, int limit, int month, int year, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new AccountNotFoundException(email));
        UUID accountId = account.getId();
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(transactionRepository.findAllByMonthAndYearAndAccount_Id(month, year, pageable, accountId));
    }

    @Override
    public Page<TransactionResponse> getByYear(int offset, int limit, int year, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new AccountNotFoundException(email));
        UUID accountId = account.getId();
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(transactionRepository.findAllByYearAndAccount_Id(year, pageable, accountId));
    }

    @Override
    public Page<TransactionResponse> getByTransactionalType
            (int offset, int limit, String transactionalType, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new AccountNotFoundException(email));
        UUID accountId = account.getId();
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(
                transactionRepository.findAllByTransactionalTypeAndAccount_Id(transactionalType, pageable, accountId)
        );
    }

    @Override // ToDo: протестить!!! возможно, нужно будет отдельный Jquery писать в репо
    public Set<TransactionResponse> getByDates(Instant startDate, Instant endDate, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new AccountNotFoundException(email));
        UUID accountId = account.getId();
        List<TransactionResponse> list =
                transactionRepository.findAllByDateBetweenAndAccount_Id(startDate, endDate, accountId)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();
        return new HashSet<>(list);
    }

    @Override
    public UUID create(TransactionRequest request, String email) {
        Account account = accountRepository.findByEmail(email).orElseThrow(()-> new AccountNotFoundException(email));
        Transaction transaction = mapper.toEntity(request);
        transaction.setAccount(account);
        transaction.setEditable(true);
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public void updateById(UUID id, TransactionRequest request, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new AccountNotFoundException(email));
        Transaction transaction = transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
        if (!account.getId().equals(transaction.getAccount().getId())){
            throw new NoAccessException();
        }
        if (transaction.isEditable()) {
            transaction.setDate(request.date());
            transaction.setAmount(request.amount());
            transaction.setCategory(request.category());
            transaction.setTransactionalType(request.transactionalType());
            transactionRepository.save(transaction);
        } else {
            throw new NoAccessException();
        }
    }

    @Override
    public void deleteById(UUID id, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(()-> new AccountNotFoundException(email));
        Transaction transaction = transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
        if (!account.getId().equals(transaction.getAccount().getId())){
            throw new NoAccessException();
        }
        if (transaction.isEditable()) {
            transactionRepository.deleteById(id);
        } else {
            throw new NoAccessException();
        }
    }
}
