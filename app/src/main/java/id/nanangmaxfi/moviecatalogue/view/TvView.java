package id.nanangmaxfi.moviecatalogue.view;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.Movie;

public interface TvView {
    void showList(ArrayList<Movie> tvShows);
    void selectTv(Movie tvShow);
}
