package com.ilyaproject.catalog.dto.write;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAuthorDTO {
    @Size(max = 100)
    private String name;
    @Size(max = 100)
    private String about;
}
