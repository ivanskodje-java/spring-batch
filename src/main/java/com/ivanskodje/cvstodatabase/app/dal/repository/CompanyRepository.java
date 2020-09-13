package com.ivanskodje.cvstodatabase.app.dal.repository;

import com.ivanskodje.cvstodatabase.app.dal.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findFirstByNameIgnoreCase(String name);
}
