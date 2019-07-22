package id.nanangmaxfi.moviecatalogue.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE type LIKE :type ")
    List<Favorite> getFavorite(String type);

    @Query("SELECT * FROM favorite")
    List<Favorite> getAllFavorite();

    @Query("SELECT * FROM favorite WHERE id LIKE :id")
    Favorite getItem(String id);

    @Insert
    void insertFavorite(Favorite... favorite);

    @Delete
    void deleteFavorite(Favorite... favorite);
}
