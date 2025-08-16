package com.ilyaproject.catalog.dto.write;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCourseDTO {
    @Size(max = 100, message = "Name max size is 100")
    private String name;

    @Size(max = 100, message = "Description max size is 100")
    private String description;

    @DecimalMin("0.00")
    @Digits(integer = 12, fraction = 2, message = "Price format is 12x.2x")
    private BigDecimal price;

    @Min(value = 0, message = "Number of spots cannot be less than 0")
    private Integer spotsLeft;

    private Long authorId;
}
