package ru.itis.financeimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.itis.financeimpl.model.Subscription;
import ru.itis.financeimpl.service.EmailSendService;

@Service
@RequiredArgsConstructor
public class EmailSendServiceImpl implements EmailSendService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(Subscription subscription) {

        String subject = "Подписка на %s скоро закончится".formatted(subscription.getName());
        String message = "Пожалуйста, продлите или отключите подписку на %s".formatted(subscription.getName());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(subscription.getAccount().getEmail());
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);

    }
}
