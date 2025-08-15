package com.ilyaproject.catalog.dto.write;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAuthorDTO {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name max size is 100")
    private String name;
    @NotBlank(message = "About cannot be empty")
    @Size(max = 100, message = "About max size is 100")
    private String about;
}
