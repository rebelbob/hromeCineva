package com.mustplay.moviejournal;

public class Movie {
    String title;
    String posterUrl;
    String score;
    String description;

    public Movie(String title, String posterUrl, String score){
        this.title = title;
        this.posterUrl = posterUrl;
        this.score = score;
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
}
