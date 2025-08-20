package com.ilyaproject.payment.dto.write;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResultDTO {
    private Long OrderId;

    private Boolean isSucceeded;
}
