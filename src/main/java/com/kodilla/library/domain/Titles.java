package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Titles")
public class Titles {

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "title_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publication_year")
    private int year;

    @OneToMany(targetEntity = Books.class,
    mappedBy = "titleId")
    private List<Books> booksList;



}
