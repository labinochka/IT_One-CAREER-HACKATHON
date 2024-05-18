package ru.itis.financeimpl.service;

import org.springframework.scheduling.annotation.Scheduled;
import ru.itis.financeimpl.model.Account;
import ru.itis.financeimpl.model.Subscription;

public interface EmailSendService {

    void checkSubscription();

    void sendEmailNotification(Subscription subscription);
}
