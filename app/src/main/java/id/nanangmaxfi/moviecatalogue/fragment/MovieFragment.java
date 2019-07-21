package id.nanangmaxfi.moviecatalogue.fragment;


import android.app.ActionBar;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.DetailActivity;
import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.adapter.MovieAdapter;
import id.nanangmaxfi.moviecatalogue.helper.ItemClickSupport;
import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.presenter.MoviePresenter;
import id.nanangmaxfi.moviecatalogue.view.MovieView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieView {
    private final static String TAG = MovieFragment.class.getSimpleName();
    private RecyclerView rvMovie;
    private ProgressBar progressBar;
    private RelativeLayout layoutError;
    private MoviePresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private final String DATA = "data";
    private ArrayList<GetMovie> movies = new ArrayList<>();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView txtTitle = view.findViewById(R.id.toolbar_title);
        txtTitle.setText(R.string.movie);
        setHasOptionsMenu(true);

        presenter = new MoviePresenter(this);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
        layoutError = view.findViewById(R.id.layout_error);
        linearLayoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(DATA, movies);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null){
            movies = savedInstanceState.getParcelableArrayList(DATA);
            showList(movies);
        }
        else {
            progressLoading(0);
            presenter.load();
        }
    }

    @Override
    public void showList(final ArrayList<GetMovie> movies) {
        this.movies = movies;
        progressLoading(1);
        rvMovie.setLayoutManager(linearLayoutManager);
        MovieAdapter adapter = new MovieAdapter(getContext(), movies);
        rvMovie.setAdapter(adapter);
        ItemClickSupport.addTo(rvMovie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                selectMovie(movies.get(position).getId());
            }
        });
    }

    @Override
    public void selectMovie(String id) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, id);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        progressLoading(2);
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.action_search){
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setQueryHint(getString(R.string.search));
            MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem menuItem) {
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                    progressLoading(0);
                    presenter.load();
                    return true;
                }
            });

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    progressLoading(0);
                    presenter.search(s);
                    return true;
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
