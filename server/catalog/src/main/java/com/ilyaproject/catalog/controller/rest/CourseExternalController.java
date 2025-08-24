package com.ilyaproject.catalog.controller.rest;

import com.ilyaproject.api.dto.catalog.CourseFullDTO;
import com.ilyaproject.catalog.constants.CourseConstants;

import com.ilyaproject.catalog.dto.general.ResponseDTO;
import com.ilyaproject.catalog.dto.write.CreateCourseDTO;
import com.ilyaproject.catalog.service.impl.CourseServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/course/create")
    public ResponseEntity<ResponseDTO> createCourse(@Valid @RequestBody CreateCourseDTO courseDTO){
        courseService.createCourse(courseDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(CourseConstants.STATUS_201, CourseConstants.MESSAGE_201));
    }


}
