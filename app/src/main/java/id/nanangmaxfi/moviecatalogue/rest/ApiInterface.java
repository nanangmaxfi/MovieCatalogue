package id.nanangmaxfi.moviecatalogue.rest;

import id.nanangmaxfi.moviecatalogue.BuildConfig;
import id.nanangmaxfi.moviecatalogue.model.GetDetailMovie;
import id.nanangmaxfi.moviecatalogue.model.GetListMovie;
import id.nanangmaxfi.moviecatalogue.model.GetListTv;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("discover/movie?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetListMovie> getListMovie();

    @GET("discover/tv?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetListTv> getListTv();

    @GET("movie/{id}?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetDetailMovie> getDetailMovie(@Path("id") String id);
}
