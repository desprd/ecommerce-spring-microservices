package com.ilyaproject.catalog.service;

import com.ilyaproject.catalog.dto.read.CourseFullDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {
    List<CourseFullDTO> fetchAllCourses();
}
