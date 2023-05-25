package com.example.codinqquiz.event;

import com.example.codinqquiz.model.User;
import com.example.codinqquiz.service.impl.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@RequiredArgsConstructor
public class AppEventListener {

    private final EmailService emailService;
    @Value("${server.port}")
    private String port;

    @EventListener
    public void handeUserRegisterEvent(UserRegisteredEvent event) {
        sendVerificationEmail(event.getUser());
    }

    @SneakyThrows
    private void sendVerificationEmail(User user) {
        String body = String.format("Your verification code for CodingQuizAccount ", user.getVerificationCode());
        emailService.sendEmail(user.getEmail(), "Account Verification", body);
    }
}
