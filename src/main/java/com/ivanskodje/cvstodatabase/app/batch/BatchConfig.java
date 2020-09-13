package com.ivanskodje.cvstodatabase.app.batch;

import com.ivanskodje.cvstodatabase.app.dto.CsvFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private static final String JOB_NAME = "myJob";
    private static final String STEP_NAME = "myStep";
    private static final int CHUNK_SIZE = 100; // TODO: move to config

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReader<CsvFileDto> fileItemReader;
    private final ItemProcessor<CsvFileDto, CsvFileDto> processor;
    private final ItemWriter<CsvFileDto> itemWriter;

    @Bean
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get(STEP_NAME)
                .<CsvFileDto, CsvFileDto>chunk(CHUNK_SIZE)
                .reader(fileItemReader)
                .processor(processor)
                .writer(itemWriter)
                .build();
    }
}
