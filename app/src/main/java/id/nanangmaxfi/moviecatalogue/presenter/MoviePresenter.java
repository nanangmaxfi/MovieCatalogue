package id.nanangmaxfi.moviecatalogue.presenter;

import android.content.res.TypedArray;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.Movie;
import id.nanangmaxfi.moviecatalogue.view.MovieView;

public class MoviePresenter {
    private MovieView view;

    public MoviePresenter(MovieView view) {
        this.view = view;
    }

    public void load(String[] dataTitle, String[] dataDesc, TypedArray dataPoster,  String[] dataScore, String[] dataYear){
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++){
            Movie movie = new Movie();
            movie.setPoster(dataPoster.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setDesc(dataDesc[i]);
            movie.setScore(dataScore[i]);
            movie.setYear(dataYear[i]);
            movies.add(movie);
        }

        view.showList(movies);
    }

}
