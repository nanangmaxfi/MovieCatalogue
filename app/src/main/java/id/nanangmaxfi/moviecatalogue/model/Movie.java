package id.nanangmaxfi.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title;
    private String desc;
    private int poster;
    private String score;
    private String year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeInt(this.poster);
        dest.writeString(this.score);
        dest.writeString(this.year);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
        this.poster = in.readInt();
        this.score = in.readString();
        this.year = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
