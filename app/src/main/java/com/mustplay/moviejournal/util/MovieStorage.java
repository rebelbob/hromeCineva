package com.mustplay.moviejournal.util;

import com.mustplay.moviejournal.Movie;

import java.util.ArrayList;
import java.util.Collection;

public class MovieStorage {
    private static MovieStorage instance = null;

    private static ArrayList<Movie> newMovies;


    private MovieStorage(){
        newMovies = new ArrayList<>();
    }

    public static synchronized MovieStorage getInstance(){
        if (instance == null){
            instance = new MovieStorage();
        }
        return instance;
    }

    synchronized public static ArrayList<Movie> getMovies(){
        return newMovies;
    }

    synchronized public static void addMovies(Collection<Movie> movie){
        newMovies.addAll(movie);
    }

    synchronized public static void addMovie(Movie movie) {
        newMovies.add(movie);
    }
}
