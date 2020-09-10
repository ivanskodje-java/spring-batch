package com.ivanskodje.cvstodatabase.app.dto;

import lombok.Data;

@Data
public class Employee {
    private String serialNumber;
    private String companyName;
    private String employeeName;
    private String description;
    private Integer leave;
}
