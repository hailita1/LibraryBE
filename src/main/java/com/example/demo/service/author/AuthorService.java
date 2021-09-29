package com.example.demo.service.author;

import com.example.demo.model.Author;
import com.example.demo.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService implements IAuthorService {
    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void remove(Long id) {
        authorRepository.deleteById(id);
    }
}
