package ru.itis.bank.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.itis.bank.dto.request.TransactionGetRequest;
import ru.itis.bank.dto.response.TransactionResponse;

import java.time.Instant;
import java.util.Set;

@Tag(name = "Transaction API")
@RequestMapping(name = "api/v1/transaction")
public interface TransactionApi {
    @GetMapping("/{mail}")
    @Operation(summary = "Получение транзакций пользователя", method = "get-user-transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Цели и лимиты получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    Set<TransactionResponse> getTransactions(@PathVariable String mail,
                                             @RequestParam Instant startDate,
                                             @RequestParam Instant endDate);
}
