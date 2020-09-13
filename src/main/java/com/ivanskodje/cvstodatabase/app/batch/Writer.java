package com.ivanskodje.cvstodatabase.app.batch;

import com.ivanskodje.cvstodatabase.app.dal.entity.CompanyEntity;
import com.ivanskodje.cvstodatabase.app.dal.entity.EmployeeEntity;
import com.ivanskodje.cvstodatabase.app.dal.entity.EmployeeHashEntity;
import com.ivanskodje.cvstodatabase.app.dal.repository.CompanyRepository;
import com.ivanskodje.cvstodatabase.app.dal.repository.EmployeeHashRepository;
import com.ivanskodje.cvstodatabase.app.dal.repository.EmployeeRepository;
import com.ivanskodje.cvstodatabase.app.dto.CsvFileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Writer implements ItemWriter<CsvFileDto> {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeHashRepository employeeHashRepository;

    // TODO: This works, but change this to do actual batch inserts, instead of one by one as it is doing right now. :)
    @Override
    public void write(List<? extends CsvFileDto> list) throws Exception {
        log.info("Writing: " + list.toString());
        int numberOfCompaniesCreated = 0;
        int numberOfEmployeesCreated = 0;
        for (CsvFileDto csvFile : list) {

            // If we have employee, skip
            if (employeeHashRepository.existsByHash(csvFile.hashCode())) {
                return;
            }

            // We do not have the employee stored. Let us see if we have the company before we even attempt to insert an employee.
            CompanyEntity companyEntity = companyRepository.findFirstByNameIgnoreCase(csvFile.getCompanyName());
            if (companyEntity == null) {
                // Create company on the fly
                companyEntity = new CompanyEntity();
                companyEntity.setName(csvFile.getCompanyName());
                companyRepository.save(companyEntity);
                numberOfCompaniesCreated++;
            }

            // Now that we have a company, create employee
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setCompany(companyEntity);
            employeeEntity.setName(csvFile.getEmployeeName());
            employeeEntity.setDescription(csvFile.getDescription());
            employeeEntity.setSerialNumber(csvFile.getSerialNumber());
            employeeEntity.setLeave(csvFile.getLeave());
            employeeRepository.save(employeeEntity);

            // Create hash to go along with the new employee
            employeeHashRepository.save(new EmployeeHashEntity(csvFile, employeeEntity));

            numberOfEmployeesCreated++;
        }
        log.info("Writing complete: Added '{}' companies, and '{}' employees", numberOfCompaniesCreated, numberOfEmployeesCreated);
    }
}
