package ru.itis.financeapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitCreateRequest;
import ru.itis.financeapi.dto.request.GoalAndBudgetLimitUpdateRequest;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitResponse;
import ru.itis.financeapi.dto.response.GoalAndBudgetLimitWithProgressResponse;

import java.util.Set;
import java.util.UUID;

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
    void create(GoalAndBudgetLimitCreateRequest saveRequest, @AuthenticationPrincipal UserDetails userDetails);

    @GetMapping("/all")
    @Operation(summary = "Получение целей и бюджетов", method = "get-all-current-goals-and-limits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цели и лимиты получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    Set<GoalAndBudgetLimitResponse> getAllCurrent(@AuthenticationPrincipal UserDetails userDetails);

    @GetMapping("/all-with-progress")
    @Operation(summary = "Получение целей и бюджетов", method = "get-all-current-goals-and-limits-with-progress")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цели и лимиты c прогрессом получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    Set<GoalAndBudgetLimitWithProgressResponse> getAllCurrentWithProgress(
            @AuthenticationPrincipal UserDetails userDetails);

    @PatchMapping
    @Operation(summary = "Обновление цели и бюджета", method = "update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление прошло успешно"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    void update(GoalAndBudgetLimitUpdateRequest updateRequest, @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Удаление цели и бюджета по id", method = "delete-by-id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цeль и бюджет удалены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @DeleteMapping
    void delete(@RequestBody UUID id, @AuthenticationPrincipal UserDetails userDetails);
}
