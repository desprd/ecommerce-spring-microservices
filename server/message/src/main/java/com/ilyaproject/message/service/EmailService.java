package com.ilyaproject.message.service;

import com.ilyaproject.message.dto.SimpleEmailMessageDTO;

public interface EmailService {
    void sendSimpleEmail(SimpleEmailMessageDTO emailDTO);
}
