package id.nanangmaxfi.moviecatalogue.presenter;

import android.content.res.TypedArray;
import android.text.TextUtils;
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

    public void search(String name){
        if (TextUtils.isEmpty(name)){
            ArrayList<GetMovie> movies = new ArrayList<>();
            view.showList(movies);
        }
        else {
            Call<GetListMovie> movieCall = apiInterface.getSearchMovie(name);
            movieCall.enqueue(new Callback<GetListMovie>() {
                @Override
                public void onResponse(Call<GetListMovie> call, Response<GetListMovie> response) {
                    Log.d(TAG, "Search success");
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

    }

}
