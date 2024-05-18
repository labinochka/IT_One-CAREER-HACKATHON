package ru.itis.financeimpl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.financeapi.dto.request.SubscriptionSaveRequest;
import ru.itis.financeapi.dto.request.SubscriptionUpdateRequest;
import ru.itis.financeapi.dto.response.SubscriptionResponse;
import ru.itis.financeimpl.model.Subscription;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    @Mapping(target = "id")
    SubscriptionResponse toResponse(Subscription subscription);

    Subscription toEntity(SubscriptionSaveRequest request);

    Subscription toEntity(SubscriptionUpdateRequest request);
}
