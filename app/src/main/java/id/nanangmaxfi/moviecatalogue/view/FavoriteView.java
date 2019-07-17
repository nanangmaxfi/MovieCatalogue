package id.nanangmaxfi.moviecatalogue.view;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.model.GetTv;

public interface FavoriteView {
    void showList(ArrayList<GetMovie> movies, ArrayList<GetTv> tvs);
    void selectMovie(String id);
    void showError(String message);
}
