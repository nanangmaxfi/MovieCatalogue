package id.nanangmaxfi.moviecatalogue.presenter;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.database.Favorite;
import id.nanangmaxfi.moviecatalogue.helper.KeyString;
import id.nanangmaxfi.moviecatalogue.view.FavoriteView;
import static id.nanangmaxfi.moviecatalogue.app.MyApp.db;

public class FavoritePresenter {
    private FavoriteView view;

    public FavoritePresenter(FavoriteView view) {
        this.view = view;
    }

    public void load(){
        ArrayList<Favorite> favMovie = new ArrayList<>(db.favoriteDao().getFavorite(KeyString.MOVIE.getValue()));
        ArrayList<Favorite> favTv = new ArrayList<>(db.favoriteDao().getFavorite(KeyString.TV_SHOW.getValue()));

        view.showList(favMovie, favTv);
    }
}
