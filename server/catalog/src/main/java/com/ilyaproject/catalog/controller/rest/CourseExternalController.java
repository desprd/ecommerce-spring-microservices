package com.ilyaproject.catalog.controller.rest;

import com.ilyaproject.catalog.dto.read.CourseFullDTO;
import com.ilyaproject.catalog.service.impl.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/rest", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class CourseExternalController {

    private final CourseServiceImpl courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseFullDTO>> fetchAllCourses(){
        List<CourseFullDTO> courses = courseService.fetchAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }
}
