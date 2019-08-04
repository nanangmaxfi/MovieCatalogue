package id.nanangmaxfi.moviecatalogue.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM "+Favorite.TABLE_NAME+" WHERE type LIKE :type ")
    List<Favorite> getFavorite(String type);

    @Query("SELECT * FROM "+Favorite.TABLE_NAME)
    List<Favorite> getAllFavorite();

    @Query("SELECT * FROM "+Favorite.TABLE_NAME+" WHERE id LIKE :id")
    Favorite getItem(String id);

    @Insert
    void insertFavorite(Favorite... favorite);

    @Delete
    void deleteFavorite(Favorite... favorite);

    @Query("SELECT * FROM "+Favorite.TABLE_NAME)
    Cursor selectAll();

    @Query("SELECT * FROM "+Favorite.TABLE_NAME+" WHERE id LIKE :id")
    Cursor selectItem(String id);
}
