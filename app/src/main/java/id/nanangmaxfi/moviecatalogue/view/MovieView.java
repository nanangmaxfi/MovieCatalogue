package id.nanangmaxfi.moviecatalogue.view;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.GetMovie;

public interface MovieView {
    void showList(ArrayList<GetMovie> movies);
    void selectMovie(GetMovie movie);
    void showError(String message);
}
