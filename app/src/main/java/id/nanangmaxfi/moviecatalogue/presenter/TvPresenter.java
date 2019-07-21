package id.nanangmaxfi.moviecatalogue.presenter;


import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.model.GetListTv;
import id.nanangmaxfi.moviecatalogue.model.GetTv;
import id.nanangmaxfi.moviecatalogue.rest.ApiClient;
import id.nanangmaxfi.moviecatalogue.rest.ApiInterface;
import id.nanangmaxfi.moviecatalogue.view.TvView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvPresenter {
    private final static String TAG = TvPresenter.class.getSimpleName();
    private TvView view;
    private ApiInterface apiInterface;

    public TvPresenter(TvView view) {
        this.view = view;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void load(){
        Call<GetListTv> tvCall = apiInterface.getListTv();
        tvCall.enqueue(new Callback<GetListTv>() {
            @Override
            public void onResponse(Call<GetListTv> call, Response<GetListTv> response) {
                Log.d(TAG, "Retrofit Success load");
                ArrayList<GetTv> tv = response.body().getListTv();
                view.showList(tv);
            }

            @Override
            public void onFailure(Call<GetListTv> call, Throwable t) {
                Log.e(TAG, t.toString());
                view.showError(t.getMessage());
            }
        });
    }

    public void search(String name){
        if (TextUtils.isEmpty(name)){
            ArrayList<GetTv> tvs = new ArrayList<>();
            view.showList(tvs);
        }
        else {
            Call<GetListTv> tvCall = apiInterface.getSearchTv(name);
            tvCall.enqueue(new Callback<GetListTv>() {
                @Override
                public void onResponse(Call<GetListTv> call, Response<GetListTv> response) {
                    Log.d(TAG, "Search success");
                    ArrayList<GetTv> tvs = response.body().getListTv();
                    view.showList(tvs);
                }

                @Override
                public void onFailure(Call<GetListTv> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    view.showError(t.getMessage());
                }
            });
        }

    }
}
