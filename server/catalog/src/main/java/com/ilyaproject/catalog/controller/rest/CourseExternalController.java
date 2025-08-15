package com.ilyaproject.catalog.controller.rest;

import com.ilyaproject.catalog.dto.read.CourseFullDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/rest", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CourseExternalController {

    @GetMapping("/get")
    public ResponseEntity<CourseFullDTO> fetchAllCourses(){
        
    }
}
