package ru.itis.financeimpl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.financeapi.dto.request.AccountSaveRequest;
import ru.itis.financeapi.dto.response.AccountResponse;
import ru.itis.financeimpl.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "id", ignore = true)
    Account toEntity(AccountSaveRequest request);
    AccountResponse toResponse(Account account);


}
