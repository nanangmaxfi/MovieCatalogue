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
import android.widget.RelativeLayout;

import java.util.ArrayList;

import id.nanangmaxfi.moviecatalogue.R;
import id.nanangmaxfi.moviecatalogue.TvDetailActivity;
import id.nanangmaxfi.moviecatalogue.adapter.FavTvAdapter;
import id.nanangmaxfi.moviecatalogue.database.Favorite;
import id.nanangmaxfi.moviecatalogue.helper.ItemClickSupport;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFavTvFragment extends Fragment {
    public static final String EXTRA_TV = "extra_tv";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RelativeLayout layoutEmpty;

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
        recyclerView = view.findViewById(R.id.rv_tv_tab);
        layoutEmpty = view.findViewById(R.id.layout_empty);
        linearLayoutManager = new LinearLayoutManager(getContext());

        if (getArguments() != null) {
            final ArrayList<Favorite> tvs = getArguments().getParcelableArrayList(EXTRA_TV);
            checkList(tvs);
        }
    }

    private void selectItem(String id){
        Intent intent = new Intent(getActivity(), TvDetailActivity.class);
        intent.putExtra(TvDetailActivity.EXTRA_TV, id);
        startActivity(intent);
    }

    private void checkList(final ArrayList<Favorite> tvs){
        if (tvs == null || tvs.isEmpty()){
            layoutEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            layoutEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(linearLayoutManager);
            FavTvAdapter tvAdapter = new FavTvAdapter(getContext(), tvs);
            recyclerView.setAdapter(tvAdapter);
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    selectItem(tvs.get(position).getId());
                }
            });
        }
    }
}
