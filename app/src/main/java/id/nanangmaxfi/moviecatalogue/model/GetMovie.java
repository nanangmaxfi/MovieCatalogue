package id.nanangmaxfi.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GetMovie implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("vote_average")
    private String vote;
    @SerializedName("poster_path")
    private String poster;

    public GetMovie() {
    }

    public GetMovie(String title, String overview, String releaseDate, String vote, String poster) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.vote = vote;
        this.poster = poster;
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

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
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
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.vote);
        dest.writeString(this.poster);
    }

    protected GetMovie(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.vote = in.readString();
        this.poster = in.readString();
    }

    public static final Parcelable.Creator<GetMovie> CREATOR = new Parcelable.Creator<GetMovie>() {
        @Override
        public GetMovie createFromParcel(Parcel source) {
            return new GetMovie(source);
        }

        @Override
        public GetMovie[] newArray(int size) {
            return new GetMovie[size];
        }
    };
}
