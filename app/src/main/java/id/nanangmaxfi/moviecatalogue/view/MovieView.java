package id.nanangmaxfi.moviecatalogue.view;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.GetMovie;

public interface MovieView {
    void showList(ArrayList<GetMovie> movies);
    void selectMovie(String id);
    void showError(String message);
}
