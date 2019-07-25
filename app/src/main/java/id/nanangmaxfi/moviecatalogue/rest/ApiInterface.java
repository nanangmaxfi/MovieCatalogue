package id.nanangmaxfi.moviecatalogue.rest;

import id.nanangmaxfi.moviecatalogue.BuildConfig;
import id.nanangmaxfi.moviecatalogue.model.GetDetailMovie;
import id.nanangmaxfi.moviecatalogue.model.GetDetailTv;
import id.nanangmaxfi.moviecatalogue.model.GetListMovie;
import id.nanangmaxfi.moviecatalogue.model.GetListTv;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/movie?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetListMovie> getListMovie();

    @GET("discover/tv?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetListTv> getListTv();

    @GET("movie/{id}?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetDetailMovie> getDetailMovie(@Path("id") String id);

    @GET("tv/{id}?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetDetailTv> getDetailTv(@Path("id") String id);

    @GET("search/movie?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetListMovie> getSearchMovie(@Query("query") String name);

    @GET("search/tv?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetListTv> getSearchTv(@Query("query") String name);

    @GET("discover/movie?api_key="+ BuildConfig.API_KEY +"&language=en-US")
    Call<GetListMovie> getListReleaseMovie(@Query("release_date.lte") String releaseDate);

}
