package id.nanangmaxfi.moviecatalogue.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.adapter.MovieAdapter;
import id.nanangmaxfi.moviecatalogue.model.GetMovie;


public class TabFavMovieFragment extends Fragment {
    private final static String TAG = TabFavMovieFragment.class.getSimpleName();
    public static final String EXTRA_MOVIE = "extra_movie";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public TabFavMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_fav_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_movie_tab);
        linearLayoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            ArrayList<GetMovie> movies = getArguments().getParcelableArrayList(EXTRA_MOVIE);
            recyclerView.setLayoutManager(linearLayoutManager);
            MovieAdapter movieAdapter = new MovieAdapter(getContext(), movies);
            recyclerView.setAdapter(movieAdapter);
        }
    }
}
