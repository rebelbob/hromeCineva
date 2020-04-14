package com.mustplay.moviejournal.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mustplay.moviejournal.Movie;
import com.mustplay.moviejournal.R;
import com.mustplay.moviejournal.adapter.MovieAdapter;
import com.mustplay.moviejournal.util.DownloadMovieTask;
import com.mustplay.moviejournal.util.ListManager;
import com.mustplay.moviejournal.util.MovieStorage;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        MovieStorage.getInstance();
        new DownloadMovieTask().execute();
        initRecyclerView(root);
        return root;
    }

    private void initRecyclerView(View root) {
        movieRecyclerView = root.findViewById(R.id.movie_recycler_view);
        //new GridLayoutManager(getContext(), 2) new LinearLayoutManager(getContext())
        movieRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        movieAdapter = new MovieAdapter();
        ListManager.getInstance(movieAdapter);
        movieRecyclerView.setAdapter(movieAdapter);
    }
}
