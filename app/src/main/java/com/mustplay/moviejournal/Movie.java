package com.mustplay.moviejournal;

import android.graphics.Bitmap;
import android.media.Image;

public class Movie {
    String title;
    String poster;
    String description;

    public Movie(String title, String poster){
        this.title = title;
        this.poster = poster;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
