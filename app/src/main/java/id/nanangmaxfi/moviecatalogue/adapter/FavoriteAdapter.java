package id.nanangmaxfi.moviecatalogue.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.fragment.TabFavMovieFragment;
import id.nanangmaxfi.moviecatalogue.fragment.TabFavTvFragment;
import id.nanangmaxfi.moviecatalogue.model.GetMovie;
import id.nanangmaxfi.moviecatalogue.model.GetTv;

public class FavoriteAdapter extends FragmentStatePagerAdapter {
    private int PAGE_COUNT;
    private ArrayList<GetMovie> movies;
    private ArrayList<GetTv> tvs;

    public FavoriteAdapter(FragmentManager fm, int PAGE_COUNT, ArrayList<GetMovie> movies, ArrayList<GetTv> tvs) {
        super(fm);
        this.PAGE_COUNT = PAGE_COUNT;
        this.movies = movies;
        this.tvs = tvs;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = new TabFavMovieFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(TabFavMovieFragment.EXTRA_MOVIE, movies);
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new TabFavTvFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList(TabFavTvFragment.EXTRA_TV, tvs);
                fragment.setArguments(bundle2);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
