package com.ilyaproject.payment.dto.write;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentInformationDTO {
    @NotNull(message = "Order id cannot be null")
    @Min(value = 1, message = "Order id cannot be less then 1")
    private Long orderId;

    @NotNull(message = "Price cannot be empty")
    @DecimalMin("0.00")
    @Digits(integer = 12, fraction = 2, message = "Price format is 12x.2x")
    private BigDecimal price;

    @NotNull(message = "Paid amount cannot be empty")
    @DecimalMin("0.00")
    @Digits(integer = 12, fraction = 2, message = "Price format is 12x.2x")
    private BigDecimal paidAmount;
}
