package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TitleResponse {

    public TitleResponse() {
        this.bookList = new ArrayList<>();
    }

    private Long id;
    private String title;
    private String author;
    private int year;
    private List<Book> bookList;

}
