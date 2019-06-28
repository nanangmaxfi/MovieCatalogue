package id.nanangmaxfi.moviecatalogue;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    public void selectMovie() {

    }
}
