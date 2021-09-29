package com.example.demo.service.document;

import com.example.demo.model.Document;
import com.example.demo.repository.IDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentService implements IDocumentService {
    @Autowired
    private IDocumentRepository documentRepository;

    @Override
    public Iterable<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }

    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public void remove(Long id) {
        documentRepository.deleteById(id);
    }
}
