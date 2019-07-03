package id.nanangmaxfi.moviecatalogue.view;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.Movie;

public interface MovieView {
    void showList(ArrayList<Movie> movies);
    void selectMovie(Movie movie);
}
