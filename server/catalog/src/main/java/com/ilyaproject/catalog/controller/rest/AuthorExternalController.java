package com.ilyaproject.catalog.controller.rest;

import com.ilyaproject.catalog.constants.AuthorConstants;
import com.ilyaproject.catalog.dto.general.ResponseDTO;
import com.ilyaproject.catalog.dto.write.CreateAuthorDTO;
import com.ilyaproject.catalog.service.impl.AuthorServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/rest/author", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class AuthorExternalController {

    private final AuthorServiceImpl authorService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAuthor(@Valid @RequestBody CreateAuthorDTO authorDTO){
        authorService.createAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AuthorConstants.STATUS_201, AuthorConstants.MESSAGE_201));
    }
}
