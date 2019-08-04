package id.nanangmaxfi.favoritemovie.data;

import android.net.Uri;

public class DatabaseContract {
    private static final String AUTHORITY = "id.nanangmaxfi.moviecatalogue";
    private static final String TABLE_NAME = "favorites";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_TYPE = "type";

    public static final Uri URI_FAVORITE = Uri.parse("content://"+AUTHORITY+"/"+ TABLE_NAME);
}
