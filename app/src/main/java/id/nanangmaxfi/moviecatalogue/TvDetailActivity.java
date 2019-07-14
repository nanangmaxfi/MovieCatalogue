package id.nanangmaxfi.moviecatalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.nanangmaxfi.moviecatalogue.adapter.GenreAdapter;
import id.nanangmaxfi.moviecatalogue.helper.ConfigUtils;
import id.nanangmaxfi.moviecatalogue.model.GetDetailTv;
import id.nanangmaxfi.moviecatalogue.presenter.TvDetailPresenter;
import id.nanangmaxfi.moviecatalogue.view.TvDetailView;

public class TvDetailActivity extends AppCompatActivity implements TvDetailView {
    public static final String EXTRA_TV = "extra_tv";
    public static final String DATA = "data_detail";
    private TvDetailPresenter presenter;
    private ImageView imgPoster;
    private TextView txtName, txtOverview, txtDate, txtRating, txtStatus, txtType;
    private String id;
    private ProgressBar progressBar;
    private ScrollView layoutDetail;
    private ConfigUtils configUtils = ConfigUtils.getInstance();
    private RecyclerView rv_genre;
    private LinearLayoutManager layoutManager;
    private RelativeLayout layoutError;
    private GetDetailTv tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
        }

        imgPoster = findViewById(R.id.img_poster);
        txtName = findViewById(R.id.name);
        txtDate = findViewById(R.id.first_air_date);
        txtRating = findViewById(R.id.rating);
        txtStatus = findViewById(R.id.status);
        txtType = findViewById(R.id.type);
        txtOverview = findViewById(R.id.overview);
        progressBar = findViewById(R.id.progress_bar);
        layoutDetail = findViewById(R.id.layout_detail);
        rv_genre = findViewById(R.id.rv_genre);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutError = findViewById(R.id.layout_error);

        presenter = new TvDetailPresenter(this);

        id = getIntent().getStringExtra(EXTRA_TV);

        if (savedInstanceState == null){
            progressLoading(0);
            presenter.load(id);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(DATA, tv);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tv = savedInstanceState.getParcelable(DATA);
        show(tv);
    }

    @Override
    public void show(GetDetailTv tv) {
        final String imageURL = "https://image.tmdb.org/t/p/w500";
        this.tv = tv;
        txtName.setText(tv.getName());
        txtRating.setText(tv.getRating());
        txtStatus.setText(tv.getStatus());
        txtType.setText(tv.getType());
        txtOverview.setText(tv.getOverview());
        txtDate.setText(configUtils.formatDate(tv.getFirstAirDate()));

        rv_genre.setLayoutManager(layoutManager);
        GenreAdapter genreAdapter = new GenreAdapter(getApplicationContext(), tv.getGenres());
        rv_genre.setAdapter(genreAdapter);

        Glide.with(getApplicationContext()).load(imageURL+tv.getPoster()).into(imgPoster);
        progressLoading(1);
    }

    @Override
    public void showError(String message) {
        progressLoading(2);
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
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
