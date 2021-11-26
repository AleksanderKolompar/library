package com.kodilla.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Titles")
public class Title {
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

    @OneToMany(targetEntity = Book.class,
            cascade = CascadeType.ALL,
            mappedBy = "title")
    private List<Book> bookList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooksList() {
        return bookList;
    }

    public void setBooksList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Title)) return false;
        Title title1 = (Title) o;
        return year == title1.year && Objects.equals(id, title1.id) && Objects.equals(title, title1.title) && Objects.equals(author, title1.author) && Objects.equals(bookList, title1.bookList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, year, bookList);
    }
}
