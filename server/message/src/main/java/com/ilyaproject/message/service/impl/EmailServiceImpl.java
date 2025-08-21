package com.ilyaproject.message.service.impl;

import com.ilyaproject.message.dto.SimpleEmailMessageDTO;
import com.ilyaproject.message.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;

    @Override
    public void sendSimpleEmail(SimpleEmailMessageDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailDTO.getSender());
        message.setTo(emailDTO.getRecipient());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getMessage());
        sender.send(message);
    }
}
