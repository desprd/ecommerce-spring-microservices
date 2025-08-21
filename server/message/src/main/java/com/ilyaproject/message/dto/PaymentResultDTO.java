package com.ilyaproject.message.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResultDTO {
    private Long OrderId;

    private Boolean isSucceeded;
}
