package id.nanangmaxfi.moviecatalogue.presenter;

import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.model.GetTv;
import id.nanangmaxfi.moviecatalogue.view.DetailView;

public class DetailPresenter {
    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    public void load(GetMovie movie){
        String title = String.valueOf(movie.getTitle());
        String desc = String.valueOf(movie.getOverview());
        String poster = movie.getPoster();
        String vote = String.valueOf(movie.getVote());
        String date = String.valueOf(movie.getReleaseDate());

        view.show(title, desc, poster, vote, date);
    }

    public void load(GetTv tvshow){
        String title = String.valueOf(tvshow.getTitle());
        String desc = String.valueOf(tvshow.getOverview());
        String poster = tvshow.getPoster();
        String vote = String.valueOf(tvshow.getVote());
        String date = String.valueOf(tvshow.getReleaseDate());

        view.show(title, desc, poster, vote, date);
    }
}
