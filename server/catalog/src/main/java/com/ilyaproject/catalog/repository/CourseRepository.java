package com.ilyaproject.catalog.repository;

import com.ilyaproject.catalog.entity.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Modifying
    @Transactional
    @Query("""
        UPDATE Course c
           SET c.spotsLeft = c.spotsLeft - 1
         WHERE c.courseId = :courseId
           AND c.spotsLeft >= 1
    """)
    int tryReserve(@Param("courseId") Long courseId);
}
