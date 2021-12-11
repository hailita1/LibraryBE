package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_publishing_company")
    private PublishingCompany publishingCompany;

    @Column
    private String publishingYear;

    @Column
    private String pageNumber;

    @Column
    private Boolean status;

    @Column
    private Long visitNumber;

    @Column
    private Date update_At;

    @Column
    private Date create_At;

    @Column
    private String fileName;

    @Column
    private String image;

    @Column
    private String mainAuthor;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.EAGER)
    @JoinTable(name = "documents_authors",
            joinColumns = {@JoinColumn(name = "id_document")},
            inverseJoinColumns = {@JoinColumn(name = "id_author")})
    private List<Author> author;
}
