package com.example.logisticproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveRedisService {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public ReactiveRedisService(ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    public Mono<Boolean> saveData(String key, String value) {
        return reactiveRedisTemplate.opsForValue().set(key, value, Duration.ofMinutes(1));
    }

    public Mono<String> getData(String key) {
        return reactiveRedisTemplate.opsForValue().get(key);
    }
}
