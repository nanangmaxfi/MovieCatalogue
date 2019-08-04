package id.nanangmaxfi.moviecatalogue.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import id.nanangmaxfi.moviecatalogue.database.AppDatabase;
import id.nanangmaxfi.moviecatalogue.database.Favorite;
import id.nanangmaxfi.moviecatalogue.database.FavoriteDao;

public class FavoriteProvider extends ContentProvider {
    public static final String AUTHORITY = "id.nanangmaxfi.moviecatalogue";

    private static final int FAVORITE = 1;
    private static final int FAVORITE_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, Favorite.TABLE_NAME, FAVORITE);
        uriMatcher.addURI(AUTHORITY, Favorite.TABLE_NAME+"/#", FAVORITE_ID);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int code = uriMatcher.match(uri);
        if (code == FAVORITE || code == FAVORITE_ID){
            Context context = getContext();
            if (context == null){
                return null;
            }

            FavoriteDao favoriteDao = AppDatabase.getInstance(context).favoriteDao();
            Cursor cursor;
            if (code == FAVORITE){
                cursor = favoriteDao.selectAll();
            }
            else {
                cursor = favoriteDao.selectItem(uri.getLastPathSegment());
            }
            return cursor;
        }
        else {
            throw new IllegalArgumentException("Unknown URI: "+uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
       return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
