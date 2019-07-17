package id.nanangmaxfi.moviecatalogue;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import id.nanangmaxfi.moviecatalogue.fragment.FavoriteFragment;
import id.nanangmaxfi.moviecatalogue.fragment.MovieFragment;
import id.nanangmaxfi.moviecatalogue.fragment.TvFragment;

public class MainActivity extends AppCompatActivity {
    private final static String FRAGMENT_TAG = "fragmenttag";
    private Fragment fragment = new MovieFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null){
            navigation.setSelectedItemId(R.id.nav_movie);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_layout, fragment, FRAGMENT_TAG)
                    .commit();
        }
        else {
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_TAG);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_layout, fragment, FRAGMENT_TAG)
                    .commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, FRAGMENT_TAG, fragment);
        super.onSaveInstanceState(outState);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_movie:
                    fragment = new MovieFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, FRAGMENT_TAG)
                            .commit();
                    return true;
                case R.id.nav_tv:
                    fragment = new TvFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, FRAGMENT_TAG)
                            .commit();
                    return true;
                case R.id.nav_favorite:
                    fragment = new FavoriteFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout, fragment, FRAGMENT_TAG)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
