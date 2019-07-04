package id.nanangmaxfi.moviecatalogue.presenter;

import id.nanangmaxfi.moviecatalogue.model.Movie;
import id.nanangmaxfi.moviecatalogue.view.DetailView;

public class DetailPresenter {
    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    public void load(Movie movie){
        String title = String.valueOf(movie.getTitle());
        String desc = String.valueOf(movie.getDesc());
        int poster = movie.getPoster();
        String score = String.valueOf(movie.getScore());
        String year = String.valueOf(movie.getYear());

        view.show(title, desc, poster, score, year);
    }
}
