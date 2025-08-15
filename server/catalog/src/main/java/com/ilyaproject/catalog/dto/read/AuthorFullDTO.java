package com.ilyaproject.catalog.dto.read;


import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorFullDTO {

    private Long id;

    private String name;

    private String about;

    private List<CourseSummaryDTO> courses;
}
