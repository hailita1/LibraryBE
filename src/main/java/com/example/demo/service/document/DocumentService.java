package com.example.demo.service.document;

import com.example.demo.model.Category;
import com.example.demo.model.Document;
import com.example.demo.repository.IDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public void deleteList(List<Long> model) {
        if (model != null) {
            for (int i = 0; i < model.size(); i++) {
                documentRepository.deleteById(model.get(i));
            }
        }
    }

    @Override
    public Iterable<Document> findAllByCategory(Category category) {
        return documentRepository.findAllByCategory(category);
    }
}
