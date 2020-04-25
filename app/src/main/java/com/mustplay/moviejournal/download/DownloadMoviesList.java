package com.mustplay.moviejournal.download;

import android.os.AsyncTask;

import com.mustplay.moviejournal.Movie;
import com.mustplay.moviejournal.util.ListManager;
import com.mustplay.moviejournal.util.MovieStorage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DownloadMoviesList extends AsyncTask<Void, Void, Void> {

    private static int pageNumber = 1;
    private static boolean isLoading = false;

    @Override
    synchronized protected Void doInBackground(Void... params) {

        isLoading = true;//не позволяет создать больше одного потока за раз

        Document doc = null;
        Elements titles;
        Elements score;
        Elements images;
        Elements genre;
        Elements country;

        try {
            doc = Jsoup.connect("https://www.megacritic.ru/novye/filmy?page=" + pageNumber)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (doc != null) {
            titles = doc.select("div.jrContentTitle").select("a");
            score = doc.select("div.jrOverallEditor").select("span.jrRatingValue").select("b");
            images = doc.select("div.jrListingThumbnail").select("img");
            genre = doc.select("div.jrFieldGroup").select("div.jrFieldRow").select("noindex");
            country = doc.select("div.jrFieldGroup").select("div.jrFieldRow");

            for (int i = 0; i < titles.size(); i++) {
                Movie movie = new Movie(
                        Movie.Status.NEW,
                        titles.get(i).text(),
                        images.get(i).absUrl("src"),
                        score.get(i).text(),
                        titles.get(i).attr("href"),
                        genre.get(i).text(),
                        country.get(i * 3 + 2).text());
                MovieStorage.addMovie(movie, Movie.Status.NEW);
            }
        }
        return null;
    }

    @Override
    synchronized protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        pageNumber++;
        ListManager.refreshList();
        isLoading = false;
    }

    public static boolean isLoading(){
        return isLoading;
    }
}