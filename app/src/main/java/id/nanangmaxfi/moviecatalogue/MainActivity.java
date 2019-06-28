package id.nanangmaxfi.moviecatalogue;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.adapter.MovieAdapter;
import id.nanangmaxfi.moviecatalogue.model.Movie;
import id.nanangmaxfi.moviecatalogue.presenter.MainPresenter;
import id.nanangmaxfi.moviecatalogue.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this, getApplicationContext());
        listView = findViewById(R.id.lv_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.clickDetail(position);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void showList(ArrayList<Movie> movies) {
        MovieAdapter adapter = new MovieAdapter(getApplicationContext(), movies);
        listView.setAdapter(adapter);
    }

    @Override
    public void selectMovie(Movie movie) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}
