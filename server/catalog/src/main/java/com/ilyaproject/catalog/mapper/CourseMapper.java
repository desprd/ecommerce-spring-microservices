package com.ilyaproject.catalog.mapper;

import com.ilyaproject.catalog.dto.read.CourseFullDTO;
import com.ilyaproject.catalog.dto.write.CreateCourseDTO;
import com.ilyaproject.catalog.entity.Author;
import com.ilyaproject.catalog.entity.Course;

public class CourseMapper {
    public static CourseFullDTO mapToCourseFullDTO(Course course){
        return CourseFullDTO
                .builder()
                .id(course.getCourseId())
                .name(course.getName())
                .price(course.getPrice())
                .description(course.getDescription())
                .author(AuthorMapper.mapToAuthorSummaryDTO(course.getAuthor()))
                .build();
    }

    public static Course mapToCourse(CreateCourseDTO courseDTO, Author author){
        return Course
                .builder()
                .price(courseDTO.getPrice())
                .name(courseDTO.getName())
                .description(courseDTO.getDescription())
                .author(author)
                .build();
    }
}
