package com.team.orm.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheService {

    @Cacheable(cacheNames = "cacheName")
    public Map<String, Object> getUser(String name) {
        return Map.of("name", name);
    }
}
