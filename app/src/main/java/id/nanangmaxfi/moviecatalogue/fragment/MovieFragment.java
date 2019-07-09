package id.nanangmaxfi.moviecatalogue.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    public final static String LIST_STATE_KEY = "rv_state";
    Parcelable listState;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
//        if (savedInstanceState != null){
//            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);
//        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MoviePresenter(this);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
        layoutError = view.findViewById(R.id.layout_error);
        linearLayoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        progressLoading(0);
        presenter.load();
    }


    @Override
    public void showList(final ArrayList<GetMovie> movies) {
        progressLoading(1);
        rvMovie.setLayoutManager(linearLayoutManager);
        MovieAdapter adapter = new MovieAdapter(getContext(), movies);
        rvMovie.setAdapter(adapter);
        ItemClickSupport.addTo(rvMovie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                selectMovie(movies.get(position));
            }
        });
    }

    @Override
    public void selectMovie(GetMovie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        intent.putExtra(DetailActivity.EXTRA_STATE, 0);
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
}
