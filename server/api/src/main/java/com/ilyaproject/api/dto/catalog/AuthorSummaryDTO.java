package com.ilyaproject.api.dto.catalog;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorSummaryDTO {

    private Long id;

    private String name;

    private String about;
}
