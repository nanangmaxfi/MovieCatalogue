package id.nanangmaxfi.moviecatalogue.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.DetailActivity;
import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.adapter.TvAdapter;
import id.nanangmaxfi.moviecatalogue.helper.ItemClickSupport;
import id.nanangmaxfi.moviecatalogue.model.GetTv;
import id.nanangmaxfi.moviecatalogue.presenter.TvPresenter;
import id.nanangmaxfi.moviecatalogue.view.TvView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment implements TvView {
    private RecyclerView rvTv;
    private ProgressBar progressBar;
    private RelativeLayout layoutError;
    private TvPresenter presenter;
    private final String DATA = "data";
    private ArrayList<GetTv> tvShows = new ArrayList<>();

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
        progressBar = view.findViewById(R.id.progress_bar);
        layoutError = view.findViewById(R.id.layout_error);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(DATA, tvShows);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null){
            tvShows = savedInstanceState.getParcelableArrayList(DATA);
            showList(tvShows);
        }
        else {
            progressLoading(0);
            presenter.load();
        }
    }


    @Override
    public void showList(final ArrayList<GetTv> tvShows) {
        this.tvShows = tvShows;
        progressLoading(1);
        rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
        TvAdapter adapter = new TvAdapter(getContext(), tvShows);
        rvTv.setAdapter(adapter);
        ItemClickSupport.addTo(rvTv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                selectTv(tvShows.get(position));
            }
        });
    }

    @Override
    public void selectTv(GetTv tvShow) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, tvShow);
        intent.putExtra(DetailActivity.EXTRA_STATE, 1);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        progressLoading(2);
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    private void progressLoading(Integer integer){
        if (integer.equals(0)){
            progressBar.setVisibility(View.VISIBLE);
            rvTv.setVisibility(View.INVISIBLE);
            layoutError.setVisibility(View.GONE);
        }
        else if(integer.equals(1)) {
            progressBar.setVisibility(View.GONE);
            rvTv.setVisibility(View.VISIBLE);
            layoutError.setVisibility(View.GONE);
        }
        else if(integer.equals(2)) {
            progressBar.setVisibility(View.GONE);
            rvTv.setVisibility(View.GONE);
            layoutError.setVisibility(View.VISIBLE);
        }
    }
}
