package id.nanangmaxfi.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetListMovie {
    @SerializedName("results")
    private ArrayList<GetMovie> listMovie;

    public GetListMovie(ArrayList<GetMovie> listMovie) {
        this.listMovie = listMovie;
    }

    public ArrayList<GetMovie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<GetMovie> listMovie) {
        this.listMovie = listMovie;
    }
}
