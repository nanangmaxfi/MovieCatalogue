package id.nanangmaxfi.favoritemovie.view;

import android.database.Cursor;

public interface MainView {
    void showError(String message);
    void postExecute(Cursor favorite);
}
