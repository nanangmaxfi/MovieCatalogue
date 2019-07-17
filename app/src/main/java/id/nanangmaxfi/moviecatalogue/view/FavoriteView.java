package id.nanangmaxfi.moviecatalogue.view;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.database.Favorite;
import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.model.GetTv;

public interface FavoriteView {
    void showList(ArrayList<Favorite> movies, ArrayList<Favorite> tvs);
    void showError(String message);
}
