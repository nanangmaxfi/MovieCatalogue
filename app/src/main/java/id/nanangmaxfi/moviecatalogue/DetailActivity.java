package id.nanangmaxfi.moviecatalogue;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.model.GetTv;
import id.nanangmaxfi.moviecatalogue.presenter.DetailPresenter;
import id.nanangmaxfi.moviecatalogue.view.DetailView;

public class DetailActivity extends AppCompatActivity implements DetailView {
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_STATE = "extra_state";
    private DetailPresenter presenter;
    private ImageView imgPoster;
    private TextView txtDesc, txtDate, txtVote;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private GetMovie movie;
    private GetTv tvshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        imgPoster = findViewById(R.id.expandedImage);
        txtDesc = findViewById(R.id.overview);
        txtVote = findViewById(R.id.vote);
        txtDate = findViewById(R.id.tgl_release);

        presenter = new DetailPresenter(this);

        if (getIntent().getIntExtra(EXTRA_STATE,-1) == 0){
            movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        }
        else if(getIntent().getIntExtra(EXTRA_STATE,-1) == 1){
            tvshow = getIntent().getParcelableExtra(EXTRA_MOVIE);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getIntExtra(EXTRA_STATE,-1) == 0){
            presenter.load(movie);
        }
        else if(getIntent().getIntExtra(EXTRA_STATE,-1) == 1){
            presenter.load(tvshow);
        }
    }

    @Override
    public void show(String title, String desc, String poster, String score, String date) {
        final String imageURL = "https://image.tmdb.org/t/p/w500";
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorPrimary));
        txtDesc.setText(desc);
        txtVote.setText(score);
        String newDateString = date;
        try {
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            newDateString = spf.format(newDate);
        }
        catch (Exception e){
            Log.e("Date", e.getMessage());
        }

        txtDate.setText(newDateString);
        Glide.with(getApplicationContext()).load(imageURL+poster).into(imgPoster);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
