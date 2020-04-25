package com.mustplay.moviejournal.util;

import com.mustplay.moviejournal.Movie;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MovieStorage {
    private static MovieStorage instance = null;

    private static LinkedHashMap<Movie, Movie.Status> movies;

    private MovieStorage(){
        movies = new LinkedHashMap<>();
    }

    public static synchronized MovieStorage getInstance(){
        if (instance == null){
            instance = new MovieStorage();
        }
        return instance;
    }

    synchronized public static ArrayList<Movie> getMovies(Movie.Status status){
        ArrayList<Movie> statusMovies = new ArrayList<>();
        for (Map.Entry<Movie, Movie.Status> set : movies.entrySet()){
            if (set.getValue() == status){
                statusMovies.add(set.getKey());
            }
        }
        return statusMovies;
    }

    //synchronized public static void addMovies(Collection<Movie> movie){ newMovies.addAll(movie);}

    synchronized public static void addMovie(Movie movie, Movie.Status status) {
        movies.put(movie, status);
    }
}
