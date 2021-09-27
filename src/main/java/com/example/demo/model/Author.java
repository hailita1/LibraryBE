package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String academicRank;

    @Column
    private String degree;
}
