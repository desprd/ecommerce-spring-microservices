package com.ilyaproject.order.dto.write;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDTO {
    @NotNull(message = "Course id cannot be null")
    private Long courseId;

    @NotNull(message = "Course id cannot be null")
    private Long customerId;
}
