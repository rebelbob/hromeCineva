package com.mustplay.moviejournal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mustplay.moviejournal.Movie;
import com.mustplay.moviejournal.R;
import com.mustplay.moviejournal.ui.MoviePageFragment;
import com.mustplay.moviejournal.util.DownloadMovieTask;
import com.mustplay.moviejournal.util.MovieStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_list, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(MovieStorage.getMovies().get(position));
        holder.currentMovie = MovieStorage.getMovies().get(position);

        if (position >= getItemCount() - (getItemCount() - 5)){
            if (!DownloadMovieTask.isLoading()) {
                new DownloadMovieTask().execute();
            }
        }
    }

    @Override
    public int getItemCount(){
        return MovieStorage.getMovies().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        public Movie currentMovie;
        public View view;

        private ImageView poster;
        private TextView title;
        private TextView description;
        private TextView score;

        public MovieViewHolder(final View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            score = itemView.findViewById(R.id.score);
            view = itemView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
                    FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, new MoviePageFragment(currentMovie));
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }

        public void bind(final Movie movie) {
            Picasso.with(view.getContext()).load(movie.getPosterUrl()).into(poster);
            title.setText(movie.getTitle());
            score.setText(movie.getScore());
        }
    }
}