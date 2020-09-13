package com.ivanskodje.cvstodatabase.app.dal.repository;

import com.ivanskodje.cvstodatabase.app.dal.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
