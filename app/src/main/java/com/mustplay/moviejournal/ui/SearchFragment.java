package com.mustplay.moviejournal.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mustplay.moviejournal.Movie;
import com.mustplay.moviejournal.R;
import com.mustplay.moviejournal.adapter.MovieAdapter;

import java.util.Arrays;
import java.util.Collection;

public class SearchFragment extends Fragment {

    private RecyclerView movieRecyclerView;
    private MovieAdapter movieAdapter;

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadMovies() {
        Collection<Movie> movies = getMovies();
        movieAdapter.setItems(movies);
    }

    private Collection<Movie> getMovies() {
        return Arrays.asList(
                new Movie("Горы","https://www.w3schools.com/w3css/img_fjords.jpg"),

                new Movie("Нз что", "https://www.w3schools.com/w3images/lights.jpg"),

                new Movie("а тут", "https://www.w3schools.com/css/img_mountains.jpg"),

                new Movie("Горы","https://www.w3schools.com/w3css/img_fjords.jpg"),

                new Movie("Нз что", "https://www.w3schools.com/w3images/lights.jpg"),

                new Movie("а тут", "https://www.w3schools.com/css/img_mountains.jpg"),
                
                new Movie("Горы","https://www.w3schools.com/w3css/img_fjords.jpg"),

                new Movie("Нз что", "https://www.w3schools.com/w3images/lights.jpg"),

                new Movie("а тут", "https://www.w3schools.com/css/img_mountains.jpg")
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        initRecyclerView(root);
        loadMovies();
        return root;
    }

    private void initRecyclerView(View root) {
        movieRecyclerView = root.findViewById(R.id.movie_recycler_view);
        //new GridLayoutManager(getContext(), 2);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieAdapter = new MovieAdapter();
        movieRecyclerView.setAdapter(movieAdapter);
    }
}
