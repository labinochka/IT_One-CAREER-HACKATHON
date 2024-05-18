package ru.itis.financeapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.financeapi.dto.request.TransactionRequest;
import ru.itis.financeapi.dto.response.TransactionResponse;

import java.time.Instant;
import java.util.Set;
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
                                     @RequestParam(defaultValue = "10") int limit,
                                     @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Получение транзакций по категории")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/category/{category}")
    Page<TransactionResponse> getByCategory(@RequestParam(defaultValue = "0") int offset,
                                            @RequestParam(defaultValue = "10") int limit,
                                            @PathVariable("category") String category,
                                            @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Получение транзакций по дате")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/date/{date}")
    Page<TransactionResponse> getByDate(@RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int limit,
                                        @PathVariable("date") Instant date,
                                        @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Получение транзакций по месяцу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/month/{month}/{year}")
    Page<TransactionResponse> getByMonth(@RequestParam(defaultValue = "0") int offset,
                                         @RequestParam(defaultValue = "10") int limit,
                                         @PathVariable("month") int month,
                                         @PathVariable("year") int year,
                                         @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Получение транзакций по году")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/year/{year}")
    Page<TransactionResponse> getByYear(@RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int limit,
                                        @PathVariable("year") int year,
                                        @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Получение транзакций по типу транзакции")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/transactionalType/{transactionalType}")
    Page<TransactionResponse> getByTransactionalType(@RequestParam(defaultValue = "0") int offset,
                                                     @RequestParam(defaultValue = "10") int limit,
                                                     @PathVariable("transactionalType") String type,
                                                     @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Получение транзакций по конкретным срокам")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/date/customDate")
    Set<TransactionResponse> getByDates(@RequestParam Instant startDate,
                                        @RequestParam Instant endDate,
                                        @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Создание транзакции")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PostMapping
    UUID create(@RequestBody TransactionRequest request, @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Обновление транзакции по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PutMapping("/{id}")
    void updateById(@PathVariable("id") UUID id,
                    @RequestBody TransactionRequest request,
                    @AuthenticationPrincipal UserDetails userDetails);

    @Operation(summary = "Удаление транзакции по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Транзакции получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") UUID id, @AuthenticationPrincipal UserDetails userDetails);
}
