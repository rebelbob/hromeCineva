package com.mustplay.moviejournal.util;

import android.os.AsyncTask;

import com.mustplay.moviejournal.Movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DownloadMovieTask extends AsyncTask<Void, Void, Void> {

    private static int pageNumber = 1;
    private static boolean isLoading = false;

    @Override
    synchronized protected Void doInBackground(Void... params) {

        isLoading = true;//не позволяет создать больше одного потока за раз

        Document doc = null;
        Elements titles;
        Elements score;
        Elements images;

        try {
            doc = Jsoup.connect("https://www.megacritic.ru/novye/filmy/2017-2019?page=" + pageNumber)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (doc != null) {
            titles = doc.select("div.jrContentTitle").select("a");
            score = doc.select("span.jrRatingValue").select("b");
            images = doc.select("div.jrListingThumbnail").select("img");

            for (int i = 0; i < titles.size(); i++) {
                Movie movie = new Movie(titles.get(i).text(), images.get(i).absUrl("src"), score.get(i).text());
                MovieStorage.addMovie(movie);
            }
        }
        return null;
    }

    @Override
    synchronized protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        pageNumber++;
        isLoading = false;
        ListManager.refreshList();
    }

    public static boolean isLoading(){
        return isLoading;
    }
}