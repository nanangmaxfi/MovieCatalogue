package id.nanangmaxfi.moviecatalogue.presenter;

import android.util.Log;

import id.nanangmaxfi.moviecatalogue.database.Favorite;
import id.nanangmaxfi.moviecatalogue.helper.KeyString;
import id.nanangmaxfi.moviecatalogue.model.GetDetailMovie;
import id.nanangmaxfi.moviecatalogue.rest.ApiClient;
import id.nanangmaxfi.moviecatalogue.rest.ApiInterface;
import id.nanangmaxfi.moviecatalogue.view.DetailView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.nanangmaxfi.moviecatalogue.app.MyApp.db;

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

    public void addFavorite(GetDetailMovie movie){
        Favorite tvFavorite = new Favorite();
        tvFavorite.setId(movie.getId());
        tvFavorite.setTitle(movie.getTitle());
        tvFavorite.setDescription(movie.getOverview());
        tvFavorite.setPoster(movie.getPoster());
        tvFavorite.setType(KeyString.MOVIE.getValue());
        db.favoriteDao().insertFavorite(tvFavorite);
        view.showSnackbar("Film Favorit berhasil ditambahkan");
    }

    public void deleteFavorite(String id){
        Favorite favorite = db.favoriteDao().getItem(id);
        db.favoriteDao().deleteFavorite(favorite);
        view.showSnackbar("Film ini dihapus dari daftar favorit");
    }
}
