package com.mustplay.moviejournal;

import com.mustplay.moviejournal.util.MovieStorage;

public class Movie {
    public enum Status{
        NEW,
        MARK
    }

    Status status;

    String title;
    String posterUrl;
    String score;
    String description;
    String genre;
    String country;
    String moviePageUrl;


    public Movie(Status status, String title, String posterUrl, String score, String moviePageUrl, String genre, String country){
        this.status = status;
        this.title = title;
        this.posterUrl = posterUrl;
        this.score = score;
        this.moviePageUrl = moviePageUrl;
        this.genre = genre;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMoviePageUrl() {
        return moviePageUrl;
    }

    public void setMoviePageUrl(String moviePageUrl) {
        this.moviePageUrl = moviePageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Status getStatus() {
        return status;
    }
}
