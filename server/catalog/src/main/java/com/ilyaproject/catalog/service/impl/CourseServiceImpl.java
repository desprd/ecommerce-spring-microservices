package com.ilyaproject.catalog.service.impl;

import com.ilyaproject.catalog.dto.read.CourseFullDTO;
import com.ilyaproject.catalog.dto.write.CreateCourseDTO;
import com.ilyaproject.catalog.entity.Author;
import com.ilyaproject.catalog.entity.Course;
import com.ilyaproject.catalog.exception.AuthorNotFoundException;
import com.ilyaproject.catalog.mapper.CourseMapper;
import com.ilyaproject.catalog.repository.AuthorRepository;
import com.ilyaproject.catalog.repository.CourseRepository;
import com.ilyaproject.catalog.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Cacheable(value = "coursesCache", sync = true)
    public List<CourseFullDTO> fetchAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseFullDTO> coursesDTO = new ArrayList<>();
        if (!courses.isEmpty()){
            coursesDTO = courses.stream().map(CourseMapper::mapToCourseFullDTO).toList();
        }
        return coursesDTO;
    }

    @Override
    @Transactional
    @CacheEvict(value = "coursesCache", allEntries = true)
    public void createCourse(CreateCourseDTO courseDTO) {
        Optional<Author> author = authorRepository.findById(courseDTO.getAuthorId());
        if (author.isEmpty()){
            throw new AuthorNotFoundException("Author with id " + courseDTO.getAuthorId() + " was not found");
        }
        Course course = CourseMapper.mapToCourse(courseDTO, author.get());
        courseRepository.save(course);
    }
}
