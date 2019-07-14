package id.nanangmaxfi.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetDetailMovie implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("vote_average")
    private String rating;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("budget")
    private String budget;
    @SerializedName("revenue")
    private String revenue;
    @SerializedName("genres")
    private ArrayList<Genre> genres;

    public GetDetailMovie() {
    }

    public GetDetailMovie(String title, String overview, String releaseDate, String rating, String poster, String budget, String revenue, ArrayList<Genre> genres) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.poster = poster;
        this.budget = budget;
        this.revenue = revenue;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.rating);
        dest.writeString(this.poster);
        dest.writeString(this.budget);
        dest.writeString(this.revenue);
        dest.writeList(this.genres);
    }

    protected GetDetailMovie(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.rating = in.readString();
        this.poster = in.readString();
        this.budget = in.readString();
        this.revenue = in.readString();
        this.genres = new ArrayList<Genre>();
        in.readList(this.genres, Genre.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetDetailMovie> CREATOR = new Parcelable.Creator<GetDetailMovie>() {
        @Override
        public GetDetailMovie createFromParcel(Parcel source) {
            return new GetDetailMovie(source);
        }

        @Override
        public GetDetailMovie[] newArray(int size) {
            return new GetDetailMovie[size];
        }
    };
}
