package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String publishingYear;

    @Column
    private String pageNumber;

    @Column
    private Boolean status;

    @Column
    private Long visitNumber;

    @Column
    private Date update_at;

    @Column
    private String fileName;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToMany(targetEntity = Author.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "documents_authors",
            joinColumns = {@JoinColumn(name = "id_document")},
            inverseJoinColumns = {@JoinColumn(name = "id_author")})
    private List<Author> author;
}
