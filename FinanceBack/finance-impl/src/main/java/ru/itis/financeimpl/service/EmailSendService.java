package ru.itis.financeimpl.service;

import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.model.Subscription;

public interface EmailSendService {

    void sendEmail(Subscription subscription);
}
