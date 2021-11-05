package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Books;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TitleDto {

    public TitleDto() {
        this.booksList = new ArrayList<>();
    }

    private Long id;
    private String title;
    private String author;
    private int year;
    private List<Books> booksList;

}
