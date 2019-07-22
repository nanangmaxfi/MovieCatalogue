package id.nanangmaxfi.moviecatalogue;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import id.nanangmaxfi.moviecatalogue.adapter.GenreAdapter;
import id.nanangmaxfi.moviecatalogue.database.Favorite;
import id.nanangmaxfi.moviecatalogue.helper.ConfigUtils;
import id.nanangmaxfi.moviecatalogue.model.GetDetailMovie;
import id.nanangmaxfi.moviecatalogue.presenter.DetailPresenter;
import id.nanangmaxfi.moviecatalogue.view.DetailView;

import static id.nanangmaxfi.moviecatalogue.app.MyApp.db;

public class DetailActivity extends AppCompatActivity implements DetailView {
    private final static String TAG = DetailActivity.class.getSimpleName();
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String DATA = "data_detail";
    private final String imageURL = "https://image.tmdb.org/t/p/w500";
    private DetailPresenter presenter;
    private ImageView imgPoster;
    private TextView txtTitle, txtDesc, txtDate, txtRating, txtBudget, txtRevenue;
    private String id;
    private ProgressBar progressBar;
    private ScrollView layoutDetail;
    private ConfigUtils configUtils = ConfigUtils.getInstance();
    private RecyclerView rv_genre;
    private LinearLayoutManager layoutManager;
    private RelativeLayout layoutError, layoutMain;
    private GetDetailMovie movie;
    private CheckBox checkFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView tlbTitle = findViewById(R.id.toolbar_title);
        tlbTitle.setText(R.string.info_movie);

        imgPoster = findViewById(R.id.img_poster);
        txtTitle = findViewById(R.id.title);
        txtDate = findViewById(R.id.release_date);
        txtRating = findViewById(R.id.rating);
        txtBudget = findViewById(R.id.budget);
        txtRevenue = findViewById(R.id.revenue);
        txtDesc = findViewById(R.id.overview);
        progressBar = findViewById(R.id.progress_bar);
        layoutDetail = findViewById(R.id.layout_detail);
        rv_genre = findViewById(R.id.rv_genre);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutError = findViewById(R.id.layout_error);
        checkFavorite = findViewById(R.id.check_favorite);
        layoutMain = findViewById(R.id.layout_main);

        presenter = new DetailPresenter(this);

        id = getIntent().getStringExtra(EXTRA_MOVIE);
        checkFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFavorite.isChecked()){
                    presenter.addFavorite(movie);
                }
                else {
                    presenter.deleteFavorite(movie.getId());
                }
            }
        });

        if (savedInstanceState == null){
            progressLoading(0);
            presenter.load(id);
        }
    }

    private void checkFavorite(){
        if (db.favoriteDao().getItem(movie.getId()) != null){
            checkFavorite.setChecked(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressLoading(0);
        presenter.load(id);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(DATA, movie);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        movie = savedInstanceState.getParcelable(DATA);
        show(movie);
    }

    @Override
    public void show(GetDetailMovie movie) {
        this.movie = movie;
        txtTitle.setText(movie.getTitle());
        txtRating.setText(movie.getRating());
        txtBudget.setText(configUtils.formatCurrency(movie.getBudget()));
        txtRevenue.setText(configUtils.formatCurrency(movie.getRevenue()));
        txtDesc.setText(movie.getOverview());
        txtDate.setText(configUtils.formatDate(movie.getReleaseDate()));

        rv_genre.setLayoutManager(layoutManager);
        GenreAdapter genreAdapter = new GenreAdapter(getApplicationContext(), movie.getGenres());
        rv_genre.setAdapter(genreAdapter);

        Glide.with(getApplicationContext()).load(imageURL+movie.getPoster()).into(imgPoster);
        checkFavorite();
        progressLoading(1);
    }

    @Override
    public void showError(String message) {
        progressLoading(2);
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(layoutMain,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void progressLoading(Integer state){
        if (state.equals(0)){
            progressBar.setVisibility(View.VISIBLE);
            layoutDetail.setVisibility(View.INVISIBLE);
            layoutError.setVisibility(View.GONE);
        }
        else if(state.equals(1)) {
            progressBar.setVisibility(View.GONE);
            layoutDetail.setVisibility(View.VISIBLE);
            layoutError.setVisibility(View.GONE);
        }
        else if(state.equals(2)) {
            progressBar.setVisibility(View.GONE);
            layoutDetail.setVisibility(View.GONE);
            layoutError.setVisibility(View.VISIBLE);
        }
    }
}
