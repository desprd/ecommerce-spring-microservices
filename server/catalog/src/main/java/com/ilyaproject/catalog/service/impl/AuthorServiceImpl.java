package com.ilyaproject.catalog.service.impl;

import com.ilyaproject.catalog.dto.write.CreateAuthorDTO;
import com.ilyaproject.catalog.entity.Author;
import com.ilyaproject.catalog.mapper.AuthorMapper;
import com.ilyaproject.catalog.repository.AuthorRepository;
import com.ilyaproject.catalog.service.AuthorService;
import com.ilyaproject.catalog.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void createAuthor(CreateAuthorDTO authorDTO) {
        Author author = AuthorMapper.mapToAuthor(authorDTO);
        authorRepository.save(author);
    }
}
