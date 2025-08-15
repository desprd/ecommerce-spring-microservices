package com.ilyaproject.catalog.service.impl;

import com.ilyaproject.catalog.dto.read.CourseFullDTO;
import com.ilyaproject.catalog.entity.Course;
import com.ilyaproject.catalog.mapper.CourseMapper;
import com.ilyaproject.catalog.repository.CourseRepository;
import com.ilyaproject.catalog.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseFullDTO> fetchAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseFullDTO> coursesDTO = new ArrayList<>();
        if (!courses.isEmpty()){
            coursesDTO = courses.stream().map(CourseMapper::mapToCourseFullDTO).toList();
        }
        return coursesDTO;
    }
}
