package com.mustplay.moviejournal;

import com.mustplay.moviejournal.util.MovieStorage;

public class Movie {
    public enum Status{
        NEW,
        MARK
    }

    Status status;

    private String title;
    private String posterUrl;
    private String score;
    private String description;
    private String genre;
    private String country;
    private String moviePageUrl;


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

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + title.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (object.getClass() != this.getClass())
            return false;
        Movie other = (Movie) object;
        if (!this.getTitle().equals(other.getTitle()))
            return false;
        return true;
    }
}
