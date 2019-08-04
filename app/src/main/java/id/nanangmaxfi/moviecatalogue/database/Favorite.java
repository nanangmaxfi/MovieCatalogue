package id.nanangmaxfi.moviecatalogue.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = Favorite.TABLE_NAME)
public class Favorite implements Parcelable {
    public static final String TABLE_NAME = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_POSTER = "poster";
    private static final String COLUMN_TYPE = "type";

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    private String id;
    @ColumnInfo(name = COLUMN_TITLE)
    private String title;
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    private String description;
    @ColumnInfo(name = COLUMN_POSTER)
    private String poster;
    @ColumnInfo(name = COLUMN_TYPE)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Favorite fromContentValue(ContentValues values){
        final Favorite favorite = new Favorite();
        favorite.setId(values.getAsString(COLUMN_ID));
        favorite.setTitle(values.getAsString(COLUMN_TITLE));
        favorite.setDescription(values.getAsString(COLUMN_DESCRIPTION));
        favorite.setPoster(values.getAsString(COLUMN_POSTER));
        favorite.setType(values.getAsString(COLUMN_TYPE));
        return favorite;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.poster);
        dest.writeString(this.type);
    }

    public Favorite() {
    }

    private Favorite(Parcel in) {
        this.id = Objects.requireNonNull(in.readString());
        this.title = in.readString();
        this.description = in.readString();
        this.poster = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}
