package com.ivanskodje.cvstodatabase.app.dal.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
}
