package com.ilyaproject.message.message;

import com.ilyaproject.message.dto.SimpleEmailMessageDTO;
import com.ilyaproject.message.service.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentMessage {

    private final EmailServiceImpl emailService;

    @Value("${app.mail.sender}")
    private String sender;

    @Value("${app.mail.recipient}")
    private String recipient;

    public void sendPaymentSuccessMessage(){
        SimpleEmailMessageDTO emailMessageDTO = SimpleEmailMessageDTO
                .builder()
                .sender(sender)
                .recipient(recipient)
                .subject("Payment was successful")
                .message("User successfully paid for the course")
                .build();
        emailService.sendSimpleEmail(emailMessageDTO);
    }
}
