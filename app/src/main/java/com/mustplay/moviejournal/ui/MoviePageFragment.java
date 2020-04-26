package com.mustplay.moviejournal.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.mustplay.moviejournal.Movie;
import com.mustplay.moviejournal.R;
import com.mustplay.moviejournal.util.RoundedBackgroundSpan;
import com.mustplay.moviejournal.download.DownloadMoviePage;
import com.mustplay.moviejournal.util.MovieStorage;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class MoviePageFragment extends Fragment {
    private View root;
    private ImageButton addMovieButton;
    private Movie movie;
    private static MoviePageFragment fragment;
    private ShimmerFrameLayout loading;

    MoviePageFragment() {}

    public MoviePageFragment(Movie movie) {
        this.movie = movie;
        fragment = this;
    }

    public static MoviePageFragment getInstance() {
        if (fragment == null) {
            fragment = new MoviePageFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_movie_page, container, false);

        addMovieButton = root.findViewById(R.id.add_movie);
        addMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieStorage.addMovie(movie, Movie.Status.MARK);
            }
        });

        loading = root.findViewById(R.id.shimmer_title);
        loading.startShimmer();

        //загрузка данных в методе onDataLoad(), а метод вызывается в DownloadMoviePage()
        new DownloadMoviePage().execute(movie);
        return root;
    }

    public void onDataLoad(){
        loading.stopShimmer();
        loading.hideShimmer();

        ImageView poster = root.findViewById(R.id.poster);
        TextView description = root.findViewById(R.id.description);
        TextView title = root.findViewById(R.id.title);
        TextView genre = root.findViewById(R.id.genre);
        TextView country = root.findViewById(R.id.country);
        TextView releaseYear = root.findViewById(R.id.release_year);
        TextView descriptionHead = root.findViewById(R.id.descriptionHead);

        System.out.println(movie.getCountry());

        Picasso.with(getContext()).load(movie.getPosterUrl()).into(poster);
        title.setText(movie.getTitle());
        List<String> tags = Arrays.asList(movie.getGenre().split(" "));
        genre.setText(setTags(tags));
        tags = Arrays.asList(movie.getCountry().split(" "));
        country.setText(setTags(tags));
        tags = Arrays.asList(movie.getReleaseYear());
        releaseYear.setText(setTags(tags));
        description.setText(movie.getDescription());
        descriptionHead.setText("Описание");
    }

    SpannableStringBuilder setTags(List<String> tags) {
        if (tags == null) {
            return null;
        }

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

        String between = " ";
        int tagStart = 0;

        //изменить размер текста в xml на больший чем здесь, чтобы смотрелось не обрезано
        float textSize = 18 * getResources().getDisplayMetrics().scaledDensity;

        for (String tag : tags) {
            // Append tag and space after
            stringBuilder.append(tag);
            stringBuilder.append(between);

            // Set span for tag
            RoundedBackgroundSpan tagSpan = new RoundedBackgroundSpan(Color.parseColor("#6DA3BE"), Color.parseColor("#F9FEFF"), textSize);
            stringBuilder.setSpan(tagSpan, tagStart, tagStart + tag.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Update to next tag start
            tagStart += tag.length() + between.length();
        }
        return stringBuilder;
    }
}
