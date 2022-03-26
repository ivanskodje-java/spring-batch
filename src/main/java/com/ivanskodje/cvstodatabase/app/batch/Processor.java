package com.ivanskodje.cvstodatabase.app.batch;

import com.ivanskodje.cvstodatabase.app.dto.CsvFileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Processor implements ItemProcessor<CsvFileDto, CsvFileDto> {

    @Override
    public CsvFileDto process(CsvFileDto csvFileDto) throws Exception {
        return csvFileDto;
    }
}
