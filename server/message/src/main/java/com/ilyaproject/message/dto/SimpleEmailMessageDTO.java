package com.ilyaproject.message.dto;


import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleEmailMessageDTO {
    private String sender;
    private String recipient;
    private String subject;
    private String message;
}
