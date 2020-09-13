package com.ivanskodje.cvstodatabase.app.dal.entity;

import com.ivanskodje.cvstodatabase.app.dto.CsvFileDto;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * This table exists to store hashes of every employee. This makes it possible to search for employees without knowing
 * their IDs set in the table you own. This circumstance can occur when you get data from a separate source that
 * does not have the IDs you already have in your own database.
 * <p>
 * This table can be deleted if you no longer expect to be importing employees from a csv file.
 */
@Data
@Entity
@Table(name = "employee_hash")
public class EmployeeHashEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EmployeeEntity employee;

    /**
     * CSV File hash
     */
    @Column(nullable = false)
    private Integer hash;

    public EmployeeHashEntity(CsvFileDto csvFileDto, EmployeeEntity employeeEntity) {
        setEmployee(employeeEntity);
        setHash(csvFileDto.hashCode());
    }
}
