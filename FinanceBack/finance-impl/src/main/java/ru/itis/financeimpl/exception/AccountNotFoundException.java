package ru.itis.financeimpl.exception;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(String email) {
        super("Account with email %s not found".formatted(email));
    }
}
