package com.ivanskodje.cvstodatabase.app.service;

import com.ivanskodje.cvstodatabase.app.dto.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchService {
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<Employee> itemReader() {


        FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<>();
        itemReader.setLinesToSkip(1);
        FileSystemResource cvsFile = new FileSystemResource("/sample-data/samplecsv20k.csv");
        itemReader.setResource(cvsFile);

        DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
        // TODO: Continue here, clean the Application class to separate older test-logic
        return null;

    }

    @Bean
    public Step chunkBasedStep() {
        return stepBuilderFactory.get("chunkBasedStep")
                .<Employee, Employee>chunk(3)
                .reader(itemReader())
                .writer(new ItemWriter<Employee>() {
                    @Override
                    public void write(List<? extends Employee> list) throws Exception {
                        log.info("Received list of size {}", list.size());
                    }
                }).build();
    }
}
