package id.nanangmaxfi.moviecatalogue.app;

import android.app.Application;
import android.arch.persistence.room.Room;

import id.nanangmaxfi.moviecatalogue.database.AppDatabase;

public class MyApp extends Application {
    public static AppDatabase db;

    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "addFavorite").allowMainThreadQueries().build();
    }

    public static synchronized MyApp getInstance() {
        return mInstance;
    }

    public AppDatabase getDatabase(){return db;
    }
}
