package id.nanangmaxfi.moviecatalogue.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.model.Movie;
import id.nanangmaxfi.moviecatalogue.view.MainView;

public class MainPresenter {
    private MainView view;
    private Context context;

    public MainPresenter(MainView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void load(){
        String[] dataTitle = context.getResources().getStringArray(R.array.data_title);
        String[] dataDesc = context.getResources().getStringArray(R.array.data_desc);
        TypedArray dataPoster = context.getResources().obtainTypedArray(R.array.data_poster);
        String[] dataScore = context.getResources().getStringArray(R.array.data_score);
        String[] dataYear = context.getResources().getStringArray(R.array.data_year);

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

    public void clickDetail(int position){
        String title = context.getResources().getStringArray(R.array.data_title)[position];
        String desc = context.getResources().getStringArray(R.array.data_desc)[position];
        int poster = context.getResources().obtainTypedArray(R.array.data_poster).getResourceId(position, -1);
        String score = context.getResources().getStringArray(R.array.data_score)[position];
        String year = context.getResources().getStringArray(R.array.data_year)[position];

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDesc(desc);
        movie.setPoster(poster);
        movie.setScore(score);
        movie.setYear(year);

        view.selectMovie(movie);
    }
}
