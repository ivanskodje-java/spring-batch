package com.ivanskodje.cvstodatabase.app.dal.repository;

import com.ivanskodje.cvstodatabase.app.dal.entity.EmployeeHashEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeHashRepository extends JpaRepository<EmployeeHashEntity, Long> {
    boolean existsByHash(Integer hash);
}
