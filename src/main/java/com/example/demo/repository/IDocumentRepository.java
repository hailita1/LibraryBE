package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentRepository extends JpaRepository<Document, Long> {
    Iterable<Document> findAllByCategory(Category category);
}
