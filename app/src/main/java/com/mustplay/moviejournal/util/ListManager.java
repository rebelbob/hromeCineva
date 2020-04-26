package com.mustplay.moviejournal.util;

import com.mustplay.moviejournal.Movie;
import com.mustplay.moviejournal.adapter.MovieAdapter;

public class ListManager {
    private static ListManager instance = null;
    private static MovieAdapter adapter;

    //обновляет RecycleView, когда DownloadMovieTask заканчивает загрузку
    private ListManager(MovieAdapter adapter){
        this.adapter = adapter;
    }

    public static synchronized ListManager getInstance(MovieAdapter adapter){
        if (instance == null){
            instance = new ListManager(adapter);
        }
        return instance;
    }

    public static synchronized void refreshList(){
        adapter.setMovies(Movie.Status.NEW);
        adapter.notifyDataSetChanged();
    }
}
