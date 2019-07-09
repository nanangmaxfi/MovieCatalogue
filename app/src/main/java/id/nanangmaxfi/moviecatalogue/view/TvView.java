package id.nanangmaxfi.moviecatalogue.view;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.GetTv;

public interface TvView {
    void showList(ArrayList<GetTv> tvShows);
    void selectTv(GetTv tvShow);
    void showError(String message);
}
