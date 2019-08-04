package id.nanangmaxfi.favoritemovie.helper;

import android.database.Cursor;

import java.util.ArrayList;

import id.nanangmaxfi.favoritemovie.data.DatabaseContract;
import id.nanangmaxfi.favoritemovie.model.Favorite;

public class MapHelper {

    public static ArrayList<Favorite> mapCursorToArrayList(Cursor cursor){
        ArrayList<Favorite> listFavorite = new ArrayList<>();

        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_DESCRIPTION));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_POSTER));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_TYPE));
            listFavorite.add(new Favorite(id,title,description,poster,type));
        }
        return listFavorite;
    }
}
