package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.author.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;

    @GetMapping
    public ResponseEntity<Iterable<Author>> getAllAuthor() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createNewAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.save(author), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        Optional<Author> authorOptional = authorService.findById(id);
        return authorOptional.map(author -> new ResponseEntity<>(author, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Optional<Author> authorOptional = authorService.findById(id);
        return authorOptional.map(author1 -> {
            author1.setId(author.getId());
            author1.setName(author.getName());
            author1.setAcademicRank(author.getAcademicRank());
            author1.setDegree(author.getDegree());
            return new ResponseEntity<>(authorService.save(author1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
        Optional<Author> authorOptional = authorService.findById(id);
        return authorOptional.map(author -> {
            authorService.remove(id);
            return new ResponseEntity<>(author, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
