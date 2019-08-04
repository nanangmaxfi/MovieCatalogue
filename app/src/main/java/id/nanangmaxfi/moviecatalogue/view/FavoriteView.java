package id.nanangmaxfi.moviecatalogue.view;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.database.Favorite;

public interface FavoriteView {
    void showList(ArrayList<Favorite> movies, ArrayList<Favorite> tvs);
    void showError(String message);
}
