package id.nanangmaxfi.moviecatalogue;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.nanangmaxfi.moviecatalogue.model.Movie;
import id.nanangmaxfi.moviecatalogue.presenter.DetailPresenter;
import id.nanangmaxfi.moviecatalogue.view.DetailView;

public class DetailActivity extends AppCompatActivity implements DetailView {
    public static final String EXTRA_MOVIE = "extra_movie";
    private DetailPresenter presenter;
    private ImageView imgPoster;
    private TextView txtTitle, txtDesc, txtYear, txtScore;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgPoster = findViewById(R.id.img_poster);
        txtTitle = findViewById(R.id.txt_title);
        txtDesc = findViewById(R.id.txt_desc);
        txtScore = findViewById(R.id.txt_score);
        txtYear = findViewById(R.id.txt_year);

        presenter = new DetailPresenter(this);
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.load(movie);
    }

    @Override
    public void show(String title, String desc, int poster, String score, String year) {
        txtTitle.setText(title);
        txtDesc.setText(desc);
        txtScore.setText(score);
        txtYear.setText(year);
        Glide.with(getApplicationContext()).load(poster).into(imgPoster);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
