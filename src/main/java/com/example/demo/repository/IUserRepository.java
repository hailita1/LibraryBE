package com.example.demo.repository;

import com.example.demo.model.auth.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends JpaRepository<Reader, Long> {
    Reader findByEmail(String email);
}
