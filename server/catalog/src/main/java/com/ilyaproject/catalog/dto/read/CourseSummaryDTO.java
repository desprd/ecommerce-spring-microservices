package com.ilyaproject.catalog.dto.read;


import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseSummaryDTO {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer spotsLeft;
}
