package com.ilyaproject.catalog.service;

import com.ilyaproject.catalog.dto.write.CreateAuthorDTO;

public interface AuthorService {
    void createAuthor(CreateAuthorDTO authorDTO);
}
