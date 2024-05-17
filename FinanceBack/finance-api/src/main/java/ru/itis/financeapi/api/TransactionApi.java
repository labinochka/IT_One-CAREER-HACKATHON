package ru.itis.financeapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;

import java.time.Instant;
import java.util.UUID;

@Tags(value = {
        @Tag(name = "TransactionApi")
})
@Schema(description = "Работа с транзакциями")
@RequestMapping("/api/v1/transaction")
public interface TransactionApi {

    @Operation(summary = "Получение всех транзакций")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/all")
    Page<TransactionResponse> getAll(@RequestParam(defaultValue = "0") int offset,
                                     @RequestParam(defaultValue = "10") int limit);

    @Operation(summary = "Получение транзакций по категории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/category")
    Page<TransactionResponse> getByCategory(@RequestParam(defaultValue = "0") int offset,
                                            @RequestParam(defaultValue = "10") int limit,
                                            String category);

    @Operation(summary = "Получение транзакций по дате")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/date")
    Page<TransactionResponse> getByDate(@RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int limit,
                                        Instant date);

    @Operation(summary = "Получение транзакций по месяцу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/month")
    Page<TransactionResponse> getByMonth(@RequestParam(defaultValue = "0") int offset,
                                         @RequestParam(defaultValue = "10") int limit,
                                         String month,
                                         int year);

    @Operation(summary = "Получение транзакций по году")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/year")
    Page<TransactionResponse> getByYear(@RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int limit,
                                        int year);

    @Operation(summary = "Создание транзакции")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PostMapping
    UUID create(TransactionRequest request);

    @Operation(summary = "Обновление транзакции по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PutMapping
    void updateById(UUID id, TransactionRequest request);

    @Operation(summary = "Удаление транзакции по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @DeleteMapping
    void deleteById(UUID id);
}
