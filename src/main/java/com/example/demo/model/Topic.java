package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean status;
}
