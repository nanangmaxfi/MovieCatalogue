package id.nanangmaxfi.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetListTv {
    @SerializedName("results")
    private ArrayList<GetTv> listTv;

    public GetListTv(ArrayList<GetTv> listTv) {
        this.listTv = listTv;
    }

    public ArrayList<GetTv> getListTv() {
        return listTv;
    }

    public void setListTv(ArrayList<GetTv> listTv) {
        this.listTv = listTv;
    }
}
