package com.ilyaproject.payment.dto.general;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {
    private String apiPath;

    private HttpStatus errorCode;

    private String errorMessage;

    private Instant errorTime;
}
