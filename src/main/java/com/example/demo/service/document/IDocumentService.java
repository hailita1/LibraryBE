package com.example.demo.service.document;

import com.example.demo.model.Category;
import com.example.demo.model.Document;
import com.example.demo.service.IGeneralService;

public interface IDocumentService extends IGeneralService<Document> {
    Iterable<Document> findAllByCategory(Category category);

    Iterable<Document> findByOrderByIdDesc();

    Iterable<Document> findByOrderByVisitNumberDesc();

    Iterable<Document> findAllByNameContainingAndOrderByVisitNumberDesc(String name);
}
