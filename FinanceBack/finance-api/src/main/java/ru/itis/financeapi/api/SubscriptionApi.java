package ru.itis.financeapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.itis.financeapi.dto.request.SubscriptionSaveRequest;
import ru.itis.financeapi.dto.request.SubscriptionUpdateRequest;
import ru.itis.financeapi.dto.response.SubscriptionResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "SubscriptionApi")
@RequestMapping("api/v1/subscription")
public interface SubscriptionApi {

    @PostMapping()
    @Operation(summary = "Сохранение подписки", method = "add")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Подписка сохранена"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    void add(@RequestBody SubscriptionSaveRequest subscriptionRequest);

    @GetMapping("/account/{accountId}")
    @Operation(summary = "Получение всех подписок пользователя", method = "get-all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Подписки получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    List<SubscriptionResponse> getAllByAccountId(@PathVariable("accountId") UUID accountId);

    @Operation(summary = "Обновление подписки", method = "update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Подписка обновлена"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PutMapping("/{id}")
    void update(@PathVariable("id") UUID id, @RequestBody SubscriptionUpdateRequest subscriptionUpdateRequest);

    @Operation(summary = "Удаление подписки по id", method = "delete-by-id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Подписка удалена"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") UUID id);

}
