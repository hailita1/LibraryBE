package com.example.demo.model.auth;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private String gender;

    @Column(unique = true)
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String catalog;

    @Column(unique = true)
    private String studentCode;

    @Column
    private String avt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;
}
