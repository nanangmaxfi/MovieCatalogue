package id.nanangmaxfi.moviecatalogue.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.adapter.FavoriteAdapter;
import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.model.GetTv;
import id.nanangmaxfi.moviecatalogue.presenter.FavoritePresenter;
import id.nanangmaxfi.moviecatalogue.view.FavoriteView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteView {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FavoritePresenter presenter;
    private final String DATA_MOVIE = "data_movie";
    private final String DATA_TV = "data_tv";
    private ArrayList<GetMovie> movies = new ArrayList<>();
    private ArrayList<GetTv> tvs = new ArrayList<>();
    private ProgressBar progressBar;
    private LinearLayout layoutDetail;
    private RelativeLayout layoutError;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FavoritePresenter(this);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.viewpager);
        progressBar = view.findViewById(R.id.progress_bar);
        layoutDetail = view.findViewById(R.id.layout_detail);
        layoutError = view.findViewById(R.id.layout_error);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.movie));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tv_show));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(DATA_MOVIE, movies);
        outState.putParcelableArrayList(DATA_TV, tvs);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null){
            movies = savedInstanceState.getParcelableArrayList(DATA_MOVIE);
            tvs = savedInstanceState.getParcelableArrayList(DATA_TV);
            showList(movies, tvs);
        }
        else {
            progressLoading(0);
            presenter.load();
        }
    }

    @Override
    public void showList(ArrayList<GetMovie> movies, ArrayList<GetTv> tvs) {
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getChildFragmentManager(),
                tabLayout.getTabCount(), movies, tvs);
        viewPager.setAdapter(favoriteAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        progressLoading(1);
    }

    @Override
    public void selectMovie(String id) {

    }

    @Override
    public void showError(String message) {
        progressLoading(2);
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
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
