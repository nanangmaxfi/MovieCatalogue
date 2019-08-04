package id.nanangmaxfi.favoritemovie;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import id.nanangmaxfi.favoritemovie.adapter.FavoriteAdapter;
import id.nanangmaxfi.favoritemovie.data.DatabaseContract;
import id.nanangmaxfi.favoritemovie.model.Favorite;
import id.nanangmaxfi.favoritemovie.view.MainView;

import static id.nanangmaxfi.favoritemovie.helper.MapHelper.mapCursorToArrayList;

public class MainActivity extends AppCompatActivity implements MainView {
    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rvMovie;
    private ProgressBar progressBar;
    private RelativeLayout layoutError;

    private FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView tlbTitle = findViewById(R.id.toolbar_title);
        tlbTitle.setText(R.string.app_name);

        rvMovie = findViewById(R.id.rv_movie);
        progressBar = findViewById(R.id.progress_bar);
        layoutError = findViewById(R.id.layout_empty);

        favoriteAdapter = new FavoriteAdapter(this);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        rvMovie.setHasFixedSize(true);
        rvMovie.setAdapter(favoriteAdapter);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver dataObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(DatabaseContract.URI_FAVORITE, true, dataObserver);
        new getData(this,this).execute();
        progressLoading(0);
    }


    @Override
    public void showError(String message) {
        progressLoading(2);
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    private void progressLoading(Integer state){

        if (state.equals(0)){
            progressBar.setVisibility(View.VISIBLE);
            rvMovie.setVisibility(View.INVISIBLE);
            layoutError.setVisibility(View.GONE);
        }
        else if(state.equals(1)) {
            progressBar.setVisibility(View.GONE);
            rvMovie.setVisibility(View.VISIBLE);
            layoutError.setVisibility(View.GONE);
        }
        else if(state.equals(2)) {
            progressBar.setVisibility(View.GONE);
            rvMovie.setVisibility(View.GONE);
            layoutError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void postExecute(Cursor favorite) {
        ArrayList<Favorite> listFavorite = mapCursorToArrayList(favorite);

        if (listFavorite.size() > 0){
            favoriteAdapter.setListFavorite(listFavorite);
            progressLoading(1);
        }
        else {
            favoriteAdapter.setListFavorite(new ArrayList<Favorite>());
            showError(getResources().getString(R.string.favorite_list_empty));

        }
    }

    private static class getData extends AsyncTask<Void, Void, Cursor>{
        private final WeakReference<Context> weakContext;
        private final WeakReference<MainView> weakCallback;

        private getData(Context context, MainView callback) {
            this.weakContext = new WeakReference<>(context);
            this.weakCallback = new WeakReference<> (callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(DatabaseContract.URI_FAVORITE,null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            weakCallback.get().postExecute(cursor);
        }
    }

    static class DataObserver extends ContentObserver{
        final Context context;

        DataObserver(Handler handler, Context context){
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            new getData(context,(MainActivity)context).execute();
        }
    }
}
