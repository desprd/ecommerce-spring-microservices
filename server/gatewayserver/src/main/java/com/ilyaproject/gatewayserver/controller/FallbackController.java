package com.ilyaproject.gatewayserver.controller;


import com.ilyaproject.api.dto.catalog.CourseFullDTO;
import com.ilyaproject.gatewayserver.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FallbackController {

    private final CacheService cacheService;

    @RequestMapping("/coursesCache")
    public Mono<?> coursesCache(){
        List<CourseFullDTO> courses = cacheService.readCourses();
        if (courses.isEmpty()){
            return Mono.just("Service is down. Please contact support for details.");
        }else {
            return Mono.just(courses);
        }
    }

    @RequestMapping("/contactSupport")
    public Mono<?> contactSupport(){
        return Mono.just("Service is down. Please contact support for details.");
    }
}
