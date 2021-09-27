package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PublishingCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private String phone;
}
