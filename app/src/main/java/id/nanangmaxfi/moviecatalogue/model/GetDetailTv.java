package id.nanangmaxfi.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetDetailTv implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("vote_average")
    private String rating;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;
    @SerializedName("genres")
    private ArrayList<Genre> genres;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster;

    public GetDetailTv() {
    }

    public GetDetailTv(String name, String firstAirDate, String rating, String status, String type, ArrayList<Genre> genres, String overview, String poster) {
        this.name = name;
        this.firstAirDate = firstAirDate;
        this.rating = rating;
        this.status = status;
        this.type = type;
        this.genres = genres;
        this.overview = overview;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.firstAirDate);
        dest.writeString(this.rating);
        dest.writeString(this.status);
        dest.writeString(this.type);
        dest.writeList(this.genres);
        dest.writeString(this.overview);
        dest.writeString(this.poster);
    }

    protected GetDetailTv(Parcel in) {
        this.name = in.readString();
        this.firstAirDate = in.readString();
        this.rating = in.readString();
        this.status = in.readString();
        this.type = in.readString();
        this.genres = new ArrayList<Genre>();
        in.readList(this.genres, Genre.class.getClassLoader());
        this.overview = in.readString();
        this.poster = in.readString();
    }

    public static final Parcelable.Creator<GetDetailTv> CREATOR = new Parcelable.Creator<GetDetailTv>() {
        @Override
        public GetDetailTv createFromParcel(Parcel source) {
            return new GetDetailTv(source);
        }

        @Override
        public GetDetailTv[] newArray(int size) {
            return new GetDetailTv[size];
        }
    };
}
