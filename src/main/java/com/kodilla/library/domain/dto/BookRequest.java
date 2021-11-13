package com.kodilla.library.domain.dto;

public class BookRequest {

    public BookRequest() {
    }

    public BookRequest(Long titleId, String status) {
        this.titleId = titleId;
    }

    private Long titleId;

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }
}
