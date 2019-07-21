package id.nanangmaxfi.moviecatalogue.fragment;


import android.content.Intent;
import android.os.Bundle;
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
import id.nanangmaxfi.moviecatalogue.TvDetailActivity;
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
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView txtTitle = view.findViewById(R.id.toolbar_title);
        txtTitle.setText(R.string.tv_show);
        setHasOptionsMenu(true);

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
                selectTv(tvShows.get(position).getId());
            }
        });
    }

    @Override
    public void selectTv(String id) {
        Intent intent = new Intent(getActivity(), TvDetailActivity.class);
        intent.putExtra(TvDetailActivity.EXTRA_TV, id);
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
