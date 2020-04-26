package com.mustplay.moviejournal.download;

import android.os.AsyncTask;

import com.mustplay.moviejournal.Movie;
import com.mustplay.moviejournal.ui.MoviePageFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DownloadMoviePage extends AsyncTask<Movie, Void, Void> {

    private static boolean isLoading = false;

    @Override
    synchronized protected Void doInBackground(Movie... params) {

        Movie curMoviePos = params[0];

        isLoading = true;//не позволяет создать больше одного потока за раз

        Document doc = null;
        Elements images;
        Elements description;
        Elements releaseYear;

        try {
            doc = Jsoup.connect("https://www.megacritic.ru" + curMoviePos.getMoviePageUrl())
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (doc != null) {
            images = doc.select("div.jrListingMainImage").select("a");
            description = doc.select("[itemprop=description]").select("p");
            releaseYear = doc.select("div.jrFieldRow").select("div.jrFieldValue").select("a");

            curMoviePos.setPosterUrl(images.get(0).attr("href"));
            curMoviePos.setDescription(description.get(1).text());
            curMoviePos.setReleaseYear(releaseYear.get(0).text());
        }
        return null;
    }

    @Override
    synchronized protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        isLoading = false;
        MoviePageFragment.getInstance().onDataLoad();
    }

    public static boolean isLoading(){
        return isLoading;
    }
}