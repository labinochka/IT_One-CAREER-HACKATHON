package ru.itis.financeapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetWithProgressResponse;

import java.util.Set;

@Tag(name = "GoalAndLimitApi")
@RequestMapping("api/v1/goalAndBudgetLimit")
public interface GoalAndBudgetLimitApi {
    @PostMapping
    @Operation(summary = "Создание целей и бюджетов", method = "create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цель и лимиты созданы"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    void create(GoalAndBudgetLimitCreateRequest saveRequest);

    @GetMapping("/all")
    @Operation(summary = "Получение целей и бюджетов", method = "get-all-current-goals-and-limits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цели и лимиты получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    Set<GoalAndBudgetResponse> getAllCurrent();

    @GetMapping("/all-with-progress")
    @Operation(summary = "Получение целей и бюджетов", method = "get-all-current-goals-and-limits-with-progress")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цели и лимиты c прогрессом получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    Set<GoalAndBudgetWithProgressResponse> getAllCurrentWithProgress();

    @PatchMapping
    @Operation(summary = "Обновление цели и бюджета", method = "update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    void update(GoalAndBudgetLimitUpdateRequest updateRequest);

    @Operation(summary = "Удаление цели и бюджета по почте пользователя", method = "delete-by-user-mail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цeль и бюджет удалены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @DeleteMapping
    void delete(@RequestBody String username);
}
