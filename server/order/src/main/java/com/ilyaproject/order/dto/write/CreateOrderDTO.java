package com.ilyaproject.order.dto.write;

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
public class CreateOrderDTO {
    @NotNull(message = "Course id cannot be null")
    @Min(value = 1, message = "Course id cannot be smaller less 1")
    private Long courseId;

    @NotNull(message = "Course id cannot be null")
    @Min(value = 1, message = "Customer id cannot be less then 1")
    private Long customerId;

    @NotNull(message = "Price cannot be empty")
    @DecimalMin("0.00")
    @Digits(integer = 12, fraction = 2, message = "Price format is 12x.2x")
    private BigDecimal price;

}
