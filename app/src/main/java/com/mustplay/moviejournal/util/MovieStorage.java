package com.mustplay.moviejournal.util;

import com.mustplay.moviejournal.Movie;

import java.util.ArrayList;
import java.util.Collection;

public class MovieStorage {
    private static MovieStorage instance = null;

    private static ArrayList<Movie> newMovies;
    private static ArrayList<Movie> markMovies;

    private MovieStorage(){
        newMovies = new ArrayList<>();
    }

    public static synchronized MovieStorage getInstance(){
        if (instance == null){
            instance = new MovieStorage();
        }
        return instance;
    }

    synchronized public static Movie getMovie(int position, Movie.Status status){
        if (status == Movie.Status.MARK){
            return markMovies.get(position);
        } else {
            return newMovies.get(position);
        }
    }

    synchronized public static ArrayList<Movie> getMovies(Movie.Status status){
        if (status == Movie.Status.MARK) {
            return markMovies;
        } else {
            return newMovies;
        }
    }

    synchronized public static void addMovies(Collection<Movie> movie){
        newMovies.addAll(movie);
    }

    synchronized public static void addMovie(Movie movie) {
        if (movie.getStatus()  == Movie.Status.MARK){
            markMovies.add(movie);
        } else {
            newMovies.add(movie);
        }
    }
}
