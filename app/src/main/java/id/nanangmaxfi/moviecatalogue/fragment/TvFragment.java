package id.nanangmaxfi.moviecatalogue.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.DetailActivity;
import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.adapter.MovieAdapter;
import id.nanangmaxfi.moviecatalogue.helper.ItemClickSupport;
import id.nanangmaxfi.moviecatalogue.model.Movie;
import id.nanangmaxfi.moviecatalogue.presenter.TvPresenter;
import id.nanangmaxfi.moviecatalogue.view.TvView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment implements TvView {
    RecyclerView rvTv;
    private TvPresenter presenter;

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new TvPresenter(this);
        rvTv = view.findViewById(R.id.rv_tv);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    private void loadData(){
        if (getContext() != null) {
            String[] dataTitle = getContext().getResources().getStringArray(R.array.data_title_tv);
            String[] dataDesc = getContext().getResources().getStringArray(R.array.data_desc_tv);
            TypedArray dataPoster = getContext().getResources().obtainTypedArray(R.array.data_poster_tv);
            String[] dataScore = getContext().getResources().getStringArray(R.array.data_score_tv);
            String[] dataYear = getContext().getResources().getStringArray(R.array.data_year_tv);

            presenter.load(dataTitle, dataDesc, dataPoster, dataScore, dataYear);
        }
    }

    @Override
    public void showList(final ArrayList<Movie> tvShows) {
        rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter adapter = new MovieAdapter(getContext(), tvShows);
        rvTv.setAdapter(adapter);
        ItemClickSupport.addTo(rvTv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                selectTv(tvShows.get(position));
            }
        });
    }

    @Override
    public void selectTv(Movie tvShow) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, tvShow);
        startActivity(intent);
    }
}
