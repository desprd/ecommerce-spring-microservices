package com.ilyaproject.catalog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    private String name;

    private String about;

    @OneToMany(mappedBy = "author")
    private List<Course> courses = new ArrayList<>();
}
