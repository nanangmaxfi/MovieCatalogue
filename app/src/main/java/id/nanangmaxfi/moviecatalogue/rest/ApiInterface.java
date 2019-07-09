package id.nanangmaxfi.moviecatalogue.rest;

import id.nanangmaxfi.moviecatalogue.BuildConfig;
import id.nanangmaxfi.moviecatalogue.model.GetListMovie;
import id.nanangmaxfi.moviecatalogue.model.GetListTv;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("movie?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetListMovie> getListMovie();

    @GET("tv?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetListTv> getListTv();
}
