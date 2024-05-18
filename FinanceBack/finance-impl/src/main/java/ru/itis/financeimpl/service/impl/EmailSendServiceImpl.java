package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.itis.financeimpl.model.Subscription;
import ru.itis.financeimpl.repository.SubscriptionRepository;
import ru.itis.financeimpl.service.EmailSendService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailSendServiceImpl implements EmailSendService {

    private final JavaMailSender mailSender;

    private final SubscriptionRepository repository;

    @Scheduled(cron = "0 0 12 * * *")
    @Override
    public void checkSubscription() {
        List<Subscription> subscriptions = repository.findAll();

        if (!subscriptions.isEmpty()) {
            for (Subscription subscription : subscriptions) {
                if (subscription.getEndDate().isBefore(Instant.from(LocalDateTime.now().plusDays(2)))) {
                    sendEmailNotification(subscription);
                }
            }
        }
    }

    @Override
    public void sendEmailNotification(Subscription subscription) {

        String subject = "Подписка на %s скоро закончится".formatted(subscription.getName());
        String message = "Пожалуйста, продлите или отключите подписку на %s".formatted(subscription.getName());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(subscription.getAccount().getEmail());
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }
}
