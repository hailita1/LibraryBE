package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Document;
import com.example.demo.service.document.IDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/documents")
@Slf4j
public class DocumentController {
    @Autowired
    private IDocumentService documentService;

    @GetMapping
    public ResponseEntity<Iterable<Document>> getAllDocument() {
        return new ResponseEntity<>(documentService.findByOrderByIdDesc(), HttpStatus.OK);
    }

    @GetMapping("/visitNumber")
    public ResponseEntity<Iterable<Document>> getAllDocumentVisitNumberDesc() {
        return new ResponseEntity<>(documentService.findByOrderByVisitNumberDesc(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewDocument(@RequestBody Document document) {
        document.setAuthor(document.getAuthor());
        document.setCreate_At(new Date());
        if (document.getImage() == null || document.getImage().equals("")) {
            document.setImage("defaul.jpg");
        }
        documentService.save(document);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
            document1.setName(document.getName());
            document1.setPublishingYear(document.getPublishingYear());
            document1.setPageNumber(document.getPageNumber());
            document1.setVisitNumber(document.getVisitNumber());
            document1.setFileName(document.getFileName());
            document1.setImage(document.getImage());
            document1.setCategory(document.getCategory());
            document1.setAuthor(document.getAuthor());
            document1.setMainAuthor(document.getMainAuthor());
            document1.setPublishingCompany(document.getPublishingCompany());
            document1.setUpdate_At(now);
            return new ResponseEntity<>(documentService.save(document1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Document> deleteDocument(@PathVariable Long id) {
        Optional<Document> DocumentOptional = documentService.findById(id);
        return DocumentOptional.map(document -> {
            documentService.remove(id);
            return new ResponseEntity<>(document, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/deleteList")
    public ResponseEntity deleteListCategory(@RequestBody List<Long> id) {
        documentService.deleteList(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/findCategories")
    public ResponseEntity findByCategory(@RequestBody Category category) {
        return new ResponseEntity<>(documentService.findAllByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Document>> getAllDocumentByName(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(documentService.findAllByNameContainingAndOrderByVisitNumberDesc(name), HttpStatus.OK);
    }
}
