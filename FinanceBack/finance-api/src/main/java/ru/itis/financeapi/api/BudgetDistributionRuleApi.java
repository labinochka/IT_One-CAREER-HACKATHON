package ru.itis.financeapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleSaveRequest;
import ru.itis.financeapi.dto.request.BudgetDistributionRuleUpdateRequest;
import ru.itis.financeapi.dto.response.BudgetDistributionRuleResponse;

import java.util.Set;
import java.util.UUID;

@Tag(name = "BudgetDistributionRuleApi")
@RequestMapping("api/v1/budgetDistributionRule")
public interface BudgetDistributionRuleApi {
    @PostMapping
    @Operation(summary = "Создание правила распределения бюджета", method = "create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Правило распределения бюджета создано"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    void create(BudgetDistributionRuleSaveRequest saveRequest);

    @PatchMapping
    @Operation(summary = "Обновление правила распределения бюджета", method = "update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    void update(BudgetDistributionRuleUpdateRequest updateRequest);

    @GetMapping
    @Operation(summary = "Получение правил распределения бюджета", method = "get-all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Правила распределения бюджета получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    Set<BudgetDistributionRuleResponse> getAll();

    @Operation(summary = "Удаления правила распределения бюджета", method = "delete-by-id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Правило распределения бюджета удалено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @DeleteMapping
    void delete(@RequestBody UUID id);
}
