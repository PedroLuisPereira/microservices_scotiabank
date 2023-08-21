package com.example.consumer.repository;

public class Movie {

    private Long Id;
    private String title;
    private String poster;

    public Movie() {
    }

    public Movie(Long id, String title, String poster) {
        Id = id;
        this.title = title;
        this.poster = poster;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

}
