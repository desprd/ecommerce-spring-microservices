package com.ilyaproject.catalog.service;

import com.ilyaproject.catalog.dto.read.CourseFullDTO;
import com.ilyaproject.catalog.dto.write.CreateCourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {
    List<CourseFullDTO> fetchAllCourses();
    void createCourse(CreateCourseDTO courseDTO);
    Boolean reserveCourse(Long courseId);
}
