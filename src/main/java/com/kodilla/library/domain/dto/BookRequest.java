package com.kodilla.library.domain.dto;

public class BookRequest {

    public BookRequest() {
    }

    public BookRequest(Long titleId, String status) {
        this.titleId = titleId;
        this.status = status;
    }

    private Long titleId;
    private String status;

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
