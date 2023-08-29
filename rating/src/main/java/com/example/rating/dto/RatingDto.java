package com.example.rating.dto;

public class RatingDto {

    private String star;

    private Long bookId;

    public RatingDto() {
    }

    public RatingDto(String star, Long bookId) {
        this.star = star;
        this.bookId = bookId;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "RatingDto [star=" + star + ", bookId=" + bookId + "]";
    }

}
