package ru.itis.financeimpl.exception;

public class TransactionNotFoundException extends NotFoundException{

    public TransactionNotFoundException() {
        super("Transaction not found");
    }
}
