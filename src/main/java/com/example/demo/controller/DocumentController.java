package com.example.demo.controller;

import com.example.demo.model.Document;
import com.example.demo.service.document.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private IDocumentService documentService;

    @GetMapping
    public ResponseEntity<Iterable<Document>> getAllDocument() {
        return new ResponseEntity<>(documentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Document> createNewDocument(@RequestBody Document document) {
        return new ResponseEntity<>(documentService.save(document), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id) {
        Optional<Document> documentOptional = documentService.findById(id);
        return documentOptional.map(document -> new ResponseEntity<>(document, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document document) {
        Optional<Document> documentOptional = documentService.findById(id);
        Date now = new Date();
        return documentOptional.map(document1 -> {
            document1.setId(document.getId());
            document1.setPublishingYear(document.getPublishingYear());
            document1.setPageNumber(document.getPageNumber());
            document1.setVisitNumber(document.getVisitNumber());
            document1.setFileName(document.getFileName());
            document1.setImage(document.getImage());
            document1.setCategory(document.getCategory());
            document1.setAuthor(document.getAuthor());
            document1.setPublishingCompany(document.getPublishingCompany());
            document1.setUpdate_At(now);
            return new ResponseEntity<>(documentService.save(document1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Document> deleteDocument(@PathVariable Long id) {
        Optional<Document> DocumentOptional = documentService.findById(id);
        return DocumentOptional.map(Document -> {
            documentService.remove(id);
            return new ResponseEntity<>(Document, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}