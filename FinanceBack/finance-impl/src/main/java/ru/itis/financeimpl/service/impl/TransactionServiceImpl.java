package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;
import ru.itis.financeimpl.exception.NoAccessException;
import ru.itis.financeimpl.exception.TransactionNotFoundException;
import ru.itis.financeimpl.mapper.TransactionMapper;
import ru.itis.financeimpl.model.Transaction;
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

    private final TransactionRepository repository;

    private final TransactionMapper mapper;

    @Override
    public Page<TransactionResponse> getAll(int offset, int limit) {
        return mapper.toResponse(repository.findAll(PageRequest.of(offset, limit,
                Sort.by(Sort.Direction.DESC, "date"))
        ));
    }

    @Override
    public Page<TransactionResponse> getByCategory(int offset, int limit, String category) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(repository.findAllByCategory(category, pageable));
    }

    @Override
    public Page<TransactionResponse> getByDate(int offset, int limit, Instant date) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(repository.findAllByDate(date, pageable));
    }

    @Override
    public Page<TransactionResponse> getByMonth(int offset, int limit, int month, int year) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(repository.findAllByMonthAndYear(month, year, pageable));
    }

    @Override
    public Page<TransactionResponse> getByYear(int offset, int limit, int year) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(repository.findAllByYear(year, pageable));
    }

    @Override
    public Page<TransactionResponse> getByTransactionalType(int offset, int limit, String transactionalType) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(repository.findAllByTransactionalType(transactionalType, pageable));
    }

    @Override // ToDo: протестить!!! возможно, нужно будет отдельный Jquery писать в репо
    public Set<TransactionResponse> getByDates(Instant startDate, Instant endDate) {
        List<TransactionResponse> list = repository.findAllByDateBetween(startDate, endDate)
                .stream()
                .map(mapper::toResponse)
                .toList();
        return new HashSet<>(list);
    }

    @Override
    public UUID create(TransactionRequest request) {
        Transaction transaction = mapper.toEntity(request);
        transaction.setEditable(true);
        return repository.save(transaction).getId();
    }

    @Override
    public void updateById(UUID id, TransactionRequest request) {
        Transaction transaction = repository.findById(id).orElseThrow(TransactionNotFoundException::new);
        if (transaction.isEditable()) {
            transaction.setDate(request.date());
            transaction.setAmount(request.amount());
            transaction.setCategory(request.category());
            transaction.setTransactionalType(request.transactionalType());
            repository.save(transaction);
        } else {
            throw new NoAccessException();
        }
    }

    @Override
    public void deleteById(UUID id) {
        Transaction transaction = repository.findById(id).orElseThrow(TransactionNotFoundException::new);
        if (transaction.isEditable()){
            repository.deleteById(id);
        } else {
            throw new NoAccessException();
        }
    }
}
