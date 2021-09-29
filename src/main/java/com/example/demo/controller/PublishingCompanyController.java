package com.example.demo.controller;

import com.example.demo.model.PublishingCompany;
import com.example.demo.service.publishing_company.IPublishingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/publishing-companies")
public class PublishingCompanyController {
    @Autowired
    private IPublishingCompanyService publishingCompanyService;

    @GetMapping
    public ResponseEntity<Iterable<PublishingCompany>> getAllPublishingCompany() {
        return new ResponseEntity<>(publishingCompanyService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublishingCompany> createNewPublishingCompany(@RequestBody PublishingCompany publishingCompany) {
        return new ResponseEntity<>(publishingCompanyService.save(publishingCompany), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublishingCompany> getPublishingCompany(@PathVariable Long id) {
        Optional<PublishingCompany> publishingCompany = publishingCompanyService.findById(id);
        return publishingCompany.map(publishingCompany1 -> new ResponseEntity<>(publishingCompany1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublishingCompany> updatePublishingCompany(@PathVariable Long id, @RequestBody PublishingCompany publishingCompany) {
        Optional<PublishingCompany> publishingCompanyOptional = publishingCompanyService.findById(id);
        return publishingCompanyOptional.map(publishingCompany1 -> {
            publishingCompany1.setId(publishingCompany.getId());
            publishingCompany1.setName(publishingCompany.getName());
            publishingCompany1.setAddress(publishingCompany.getAddress());
            publishingCompany1.setEmail(publishingCompany.getEmail());
            publishingCompany1.setPhone(publishingCompany.getPhone());
            return new ResponseEntity<>(publishingCompanyService.save(publishingCompany1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PublishingCompany> deletePublishingCompany(@PathVariable Long id) {
        Optional<PublishingCompany> publishingCompanyOptional = publishingCompanyService.findById(id);
        return publishingCompanyOptional.map(publishingCompany -> {
            publishingCompanyService.remove(id);
            return new ResponseEntity<>(publishingCompany, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
