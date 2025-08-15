package com.ilyaproject.catalog.mapper;

import com.ilyaproject.catalog.dto.read.CourseFullDTO;
import com.ilyaproject.catalog.entity.Course;

public class CourseMapper {
    public static CourseFullDTO mapToCourseFullDTO(Course course){
        return CourseFullDTO
                .builder()
                .id(course.getCourseId())
                .name(course.getName())
                .description(course.getDescription())
                .author(AuthorMapper.mapToAuthorSummaryDTO(course.getAuthor()))
                .build();
    }
}
