package com.example.logisticproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@EnableScheduling
public class LogisticProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticProjectApplication.class, args);
    }

}
