package id.nanangmaxfi.moviecatalogue.presenter;

import android.content.res.TypedArray;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.Movie;
import id.nanangmaxfi.moviecatalogue.view.TvView;

public class TvPresenter {
    private TvView view;

    public TvPresenter(TvView view) {
        this.view = view;
    }

    public void load(String[] dataTitle, String[] dataDesc, TypedArray dataPoster, String[] dataScore, String[] dataYear){
        ArrayList<Movie> tvShows = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++){
            Movie tvShow = new Movie();
            tvShow.setPoster(dataPoster.getResourceId(i, -1));
            tvShow.setTitle(dataTitle[i]);
            tvShow.setDesc(dataDesc[i]);
            tvShow.setScore(dataScore[i]);
            tvShow.setYear(dataYear[i]);
            tvShows.add(tvShow);
        }

        view.showList(tvShows);
    }
}
