package com.mustplay.moviejournal.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.mustplay.moviejournal.Movie;
import com.mustplay.moviejournal.R;
import com.squareup.picasso.Picasso;

public class MoviePageFragment extends Fragment {
    Movie movie;

    public MoviePageFragment(Movie movie) {
        this.movie = movie;
    }
    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie_page, container, false);

        ImageView poster = root.findViewById(R.id.poster);
        Picasso.with(getContext()).load(movie.getPosterUrl()).into(poster);
        return root;
    }
}
