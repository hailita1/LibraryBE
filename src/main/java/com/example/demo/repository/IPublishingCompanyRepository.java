package com.example.demo.repository;

import com.example.demo.model.PublishingCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublishingCompanyRepository extends JpaRepository<PublishingCompany, Long> {
}
