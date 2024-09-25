package com.example.logisticproject.service;

import org.aspectj.weaver.tools.cache.CacheKeyResolver;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReactiveRedisService {


    private final ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate;

    public ReactiveRedisService(ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }


    @Cacheable(cacheNames = "photos", key = "#key", unless = "#result==false")
    public boolean getPhotoData(String key) {


        if (reactiveRedisTemplate.opsForValue().get(key).block() == null) {
            return false;
        } else return true;
    }


    @Cacheable(cacheNames = "photos", key = "#photoId")
    public boolean savePhotoData(String photoId) {
        return true;
    }
}
