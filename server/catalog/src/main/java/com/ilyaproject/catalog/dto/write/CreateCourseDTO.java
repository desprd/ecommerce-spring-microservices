package com.ilyaproject.catalog.dto.write;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCourseDTO {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name max size is 100")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 100, message = "Description max size is 100")
    private String description;

    @NotNull(message = "Price cannot be empty")
    @DecimalMin("0.00")
    @Digits(integer = 12, fraction = 2, message = "Price format is 12x.2x")
    private BigDecimal price;

    @NotNull(message = "Author id cannot be empty")
    private Long authorId;
}
