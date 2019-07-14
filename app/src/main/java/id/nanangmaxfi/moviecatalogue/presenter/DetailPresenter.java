package id.nanangmaxfi.moviecatalogue.presenter;

import android.util.Log;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.Genre;
import id.nanangmaxfi.moviecatalogue.model.GetDetailMovie;
import id.nanangmaxfi.moviecatalogue.model.GetTv;
import id.nanangmaxfi.moviecatalogue.rest.ApiClient;
import id.nanangmaxfi.moviecatalogue.rest.ApiInterface;
import id.nanangmaxfi.moviecatalogue.view.DetailView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {
    private final static String TAG = DetailPresenter.class.getSimpleName();
    private DetailView view;
    private ApiInterface apiInterface;

    public DetailPresenter(DetailView view) {
        this.view = view;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void load(String id){
        Call<GetDetailMovie> detailMovieCall = apiInterface.getDetailMovie(id);
        detailMovieCall.enqueue(new Callback<GetDetailMovie>() {
            @Override
            public void onResponse(Call<GetDetailMovie> call, Response<GetDetailMovie> response) {
                Log.d(TAG, "Retrofit Success load");
                GetDetailMovie movie = response.body();

                view.show(movie);
            }

            @Override
            public void onFailure(Call<GetDetailMovie> call, Throwable t) {
                Log.e(TAG, t.toString());
                view.showError(t.getMessage());
            }
        });

    }
}
