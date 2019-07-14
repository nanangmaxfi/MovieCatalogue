package id.nanangmaxfi.moviecatalogue.view;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.Genre;
import id.nanangmaxfi.moviecatalogue.model.GetDetailMovie;

public interface DetailView {
    void show(GetDetailMovie movie);
    void showError(String message);
}
