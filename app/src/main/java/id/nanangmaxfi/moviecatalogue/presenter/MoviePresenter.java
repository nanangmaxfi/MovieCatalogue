package id.nanangmaxfi.moviecatalogue.presenter;

import android.content.res.TypedArray;
import android.util.Log;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.GetListMovie;
import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.rest.ApiClient;
import id.nanangmaxfi.moviecatalogue.rest.ApiInterface;
import id.nanangmaxfi.moviecatalogue.view.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {
    private final static String TAG = MoviePresenter.class.getSimpleName();
    private MovieView view;
    private ApiInterface apiInterface;

    public MoviePresenter(MovieView view) {
        this.view = view;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void load(){
        Call<GetListMovie> movieCall = apiInterface.getListMovie();
        movieCall.enqueue(new Callback<GetListMovie>() {
            @Override
            public void onResponse(Call<GetListMovie> call, Response<GetListMovie> response) {
                Log.d(TAG, "Retrofit Success load");
                ArrayList<GetMovie> movies = response.body().getListMovie();
                view.showList(movies);
            }

            @Override
            public void onFailure(Call<GetListMovie> call, Throwable t) {
                Log.e(TAG, t.toString());
                view.showError(t.getMessage());
            }
        });
    }

//    public void load(String[] dataTitle, String[] dataDesc, TypedArray dataPoster,  String[] dataScore, String[] dataYear){
//        ArrayList<Movie> movies = new ArrayList<>();
//
//        for (int i = 0; i < dataTitle.length; i++){
//            Movie movie = new Movie();
//            movie.setPoster(dataPoster.getResourceId(i, -1));
//            movie.setTitle(dataTitle[i]);
//            movie.setDesc(dataDesc[i]);
//            movie.setScore(dataScore[i]);
//            movie.setYear(dataYear[i]);
//            movies.add(movie);
//        }
//
//        view.showList(movies);
//    }

}
