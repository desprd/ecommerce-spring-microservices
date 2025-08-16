package com.ilyaproject.catalog.dto.read;

import lombok.*;

import java.math.BigDecimal;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseFullDTO {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer spotsLeft;

    private AuthorSummaryDTO author;
}
