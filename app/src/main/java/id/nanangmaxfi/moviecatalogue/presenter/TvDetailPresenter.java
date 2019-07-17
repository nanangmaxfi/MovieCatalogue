package id.nanangmaxfi.moviecatalogue.presenter;

import android.util.Log;

import id.nanangmaxfi.moviecatalogue.database.Favorite;
import id.nanangmaxfi.moviecatalogue.helper.KeyString;
import id.nanangmaxfi.moviecatalogue.model.GetDetailTv;
import id.nanangmaxfi.moviecatalogue.rest.ApiClient;
import id.nanangmaxfi.moviecatalogue.rest.ApiInterface;
import id.nanangmaxfi.moviecatalogue.view.TvDetailView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.nanangmaxfi.moviecatalogue.app.MyApp.db;

public class TvDetailPresenter {
    private final static String TAG = TvDetailPresenter.class.getSimpleName();
    private TvDetailView view;
    private ApiInterface apiInterface;

    public TvDetailPresenter(TvDetailView view) {
        this.view = view;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void load(String id){
        Call<GetDetailTv> detailTvCall = apiInterface.getDetailTv(id);
        detailTvCall.enqueue(new Callback<GetDetailTv>() {
            @Override
            public void onResponse(Call<GetDetailTv> call, Response<GetDetailTv> response) {
                Log.d(TAG, "Retrofit Success load");
                GetDetailTv tv = response.body();
                view.show(tv);
            }

            @Override
            public void onFailure(Call<GetDetailTv> call, Throwable t) {
                Log.e(TAG, t.toString());
                view.showError(t.getMessage());
            }
        });
    }

    public void addFavorite(GetDetailTv tv){
        Favorite tvFavorite = new Favorite();
        tvFavorite.setId(tv.getId());
        tvFavorite.setTitle(tv.getName());
        tvFavorite.setDescription(tv.getOverview());
        tvFavorite.setPoster(tv.getPoster());
        tvFavorite.setType(KeyString.TV_SHOW.getValue());
        db.favoriteDao().insertFavorite(tvFavorite);
        view.showSnackbar("TV Show Favorit berhasil ditambahkan");
    }

    public void deleteFavorite(String id){
        Favorite favorite = db.favoriteDao().getItem(id);
        db.favoriteDao().deleteFavorite(favorite);
        view.showSnackbar("TV Show ini dihapus dari daftar favorit");
    }
}
