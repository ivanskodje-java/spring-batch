package com.ivanskodje.cvstodatabase.app.dto;

import lombok.Data;

@Data
public class CsvFileDto {

    private String serialNumber;
    // Note that in the CSV we only get company name, so we will have to process and set the correct companyId, or create it on the fly
    private String companyName;
    private String employeeName;
    private String description;
    private Integer leave;
}
