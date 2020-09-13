package com.ivanskodje.cvstodatabase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        setupTimeZone();
        SpringApplication springApplication = new SpringApplication(Application.class);
        populateDefaultProperties(springApplication);
        springApplication.run(args);
    }

    private static void setupTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
        log.info("The default time zone set to UTC for the application");
    }

    private static void populateDefaultProperties(SpringApplication springApplication) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("spring.profiles.default", "dev");
        springApplication.setDefaultProperties(properties);
    }
}
