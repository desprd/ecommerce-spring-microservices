package com.ilyaproject.catalog.mapper;

import com.ilyaproject.catalog.dto.read.AuthorSummaryDTO;
import com.ilyaproject.catalog.entity.Author;

public class AuthorMapper {
    public static AuthorSummaryDTO mapToAuthorSummaryDTO(Author author){
        return AuthorSummaryDTO
                .builder()
                .id(author.getAuthorId())
                .about(author.getAbout())
                .name(author.getName())
                .build();
    }
}
