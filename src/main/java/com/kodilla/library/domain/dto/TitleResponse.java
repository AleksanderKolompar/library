package com.kodilla.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TitleResponse {

    public TitleResponse() {
    }

    private Long id;
    private String title;
    private String author;
    private int year;

}
