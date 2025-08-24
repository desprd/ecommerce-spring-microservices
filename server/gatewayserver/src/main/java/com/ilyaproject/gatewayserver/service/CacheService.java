package com.ilyaproject.gatewayserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilyaproject.api.dto.catalog.CourseFullDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<CourseFullDTO> readCourses() {
        Object raw = redisTemplate.opsForValue().get("coursesCache::SimpleKey []");
        if (raw == null) return List.of();

        @SuppressWarnings("unchecked")
        List<?> items = (List<?>) raw;

        return items.stream()
                .map(it -> objectMapper.convertValue(it, CourseFullDTO.class))
                .toList();
    }
}
