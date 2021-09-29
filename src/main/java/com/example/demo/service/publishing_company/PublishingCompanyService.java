package com.example.demo.service.publishing_company;

import com.example.demo.model.PublishingCompany;
import com.example.demo.repository.IPublishingCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublishingCompanyService implements IPublishingCompanyService {
    @Autowired
    private IPublishingCompanyRepository publishingCompanyRepository;

    @Override
    public Iterable<PublishingCompany> findAll() {
        return publishingCompanyRepository.findAll();
    }

    @Override
    public Optional<PublishingCompany> findById(Long id) {
        return publishingCompanyRepository.findById(id);
    }

    @Override
    public PublishingCompany save(PublishingCompany publishingCompany) {
        return publishingCompanyRepository.save(publishingCompany);
    }

    @Override
    public void remove(Long id) {
        publishingCompanyRepository.deleteById(id);
    }
}
