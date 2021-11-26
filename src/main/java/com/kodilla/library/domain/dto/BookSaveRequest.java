package com.kodilla.library.domain.dto;

public class BookSaveRequest {

    private Long titleId;

    public BookSaveRequest(Long titleId) {
        this.titleId = titleId;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }
}
