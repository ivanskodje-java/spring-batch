package com.ivanskodje.cvstodatabase.app.batch;

import com.ivanskodje.cvstodatabase.app.dto.CsvFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Slf4j
@Component
public class Reader extends FlatFileItemReader<CsvFileDto> {
    @Value("${csvfile}")
    private FileSystemResource csvFilePath;

    @PostConstruct
    public void init() {
        setName("CsvFileReader");
        setResource(csvFilePath);
        setLinesToSkip(1);
        setLineMapper(lineMapper());
    }

    private LineMapper<CsvFileDto> lineMapper() {
        DefaultLineMapper<CsvFileDto> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("serialNumber", "companyName", "employeeName", "description", "leave");

        BeanWrapperFieldSetMapper<CsvFileDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(CsvFileDto.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        return defaultLineMapper;
    }
}
