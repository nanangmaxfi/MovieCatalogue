package id.nanangmaxfi.moviecatalogue.fragment;


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

import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.adapter.TvAdapter;
import id.nanangmaxfi.moviecatalogue.model.GetTv;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFavTvFragment extends Fragment {
    public static final String EXTRA_TV = "extra_tv";

    public TabFavTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_fav_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tv_tab);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        if (getArguments() != null) {
            ArrayList<GetTv> movies = getArguments().getParcelableArrayList(EXTRA_TV);
            recyclerView.setLayoutManager(linearLayoutManager);
            TvAdapter tvAdapter = new TvAdapter(getContext(), movies);
            recyclerView.setAdapter(tvAdapter);
        }
    }
}
