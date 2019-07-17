package id.nanangmaxfi.moviecatalogue.view;

import id.nanangmaxfi.moviecatalogue.model.GetDetailTv;

public interface TvDetailView {
    void show(GetDetailTv tv);
    void showError(String message);
    void showSnackbar(String message);
}
