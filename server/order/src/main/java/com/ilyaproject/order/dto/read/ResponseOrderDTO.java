package com.ilyaproject.order.dto.read;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrderDTO {
    private Long orderId;

    private Long courseId;

    private Long customerId;

    private BigDecimal price;

    private String status;
}
