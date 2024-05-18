package ru.itis.financeimpl.exception;

import org.springframework.http.HttpStatus;

public class NoAccessException extends ServiceException {

    public NoAccessException() {
        super("No access", HttpStatus.FORBIDDEN);
    }
}
