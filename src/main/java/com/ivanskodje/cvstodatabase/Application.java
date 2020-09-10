package com.ivanskodje.cvstodatabase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
@EnableBatchProcessing
public class Application {

    // TODO: Clean here, this was just for testing
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job deliverPackageJob() {
        return jobBuilderFactory.get("deliveryPackageJob").start(packageItemStep()).build();
    }

    @Bean
    public Step packageItemStep() {
        return stepBuilderFactory.get("packageItemStep").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("The item has been packaged");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

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
