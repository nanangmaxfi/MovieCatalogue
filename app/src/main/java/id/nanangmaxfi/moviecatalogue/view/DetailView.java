package id.nanangmaxfi.moviecatalogue.view;

import id.nanangmaxfi.moviecatalogue.model.GetDetailMovie;

public interface DetailView {
    void show(GetDetailMovie movie);
    void showError(String message);
    void showSnackbar(String message);
}
