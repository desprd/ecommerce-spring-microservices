package com.ilyaproject.catalog.dto.write;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCourseDTO {
    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String description;

    @DecimalMin("0.00")
    @Digits(integer = 12, fraction = 2)
    private BigDecimal price;

    private Long authorId;
}
