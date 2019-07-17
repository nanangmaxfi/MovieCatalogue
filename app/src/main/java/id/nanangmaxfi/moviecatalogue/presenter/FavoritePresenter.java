package id.nanangmaxfi.moviecatalogue.presenter;

import android.util.Log;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.GetListMovie;
import id.nanangmaxfi.moviecatalogue.model.GetListTv;
import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.model.GetTv;
import id.nanangmaxfi.moviecatalogue.rest.ApiClient;
import id.nanangmaxfi.moviecatalogue.rest.ApiInterface;
import id.nanangmaxfi.moviecatalogue.view.FavoriteView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritePresenter {
    private final static String TAG = FavoritePresenter.class.getSimpleName();
    private FavoriteView view;
    private ApiInterface apiInterface;

    public FavoritePresenter(FavoriteView view) {
        this.view = view;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void load(){
        Call<GetListMovie> movieCall = apiInterface.getListMovie();
        movieCall.enqueue(new Callback<GetListMovie>() {
            @Override
            public void onResponse(Call<GetListMovie> call, Response<GetListMovie> response) {
                Log.d(TAG, "Success get list movie");
                ArrayList<GetMovie> movies = response.body().getListMovie();
                loadTV(movies);
            }

            @Override
            public void onFailure(Call<GetListMovie> call, Throwable t) {
                Log.e(TAG, t.toString());
                view.showError(t.getMessage());
            }
        });
    }

    private void loadTV(final ArrayList<GetMovie> movies){
        Call<GetListTv> tvCall = apiInterface.getListTv();
        tvCall.enqueue(new Callback<GetListTv>() {
            @Override
            public void onResponse(Call<GetListTv> call, Response<GetListTv> response) {
                Log.d(TAG, "Success get list tv");
                ArrayList<GetTv> tvs = response.body().getListTv();
                view.showList(movies, tvs);
            }

            @Override
            public void onFailure(Call<GetListTv> call, Throwable t) {
                Log.e(TAG, t.toString());
                view.showError(t.getMessage());
            }
        });
    }
}
