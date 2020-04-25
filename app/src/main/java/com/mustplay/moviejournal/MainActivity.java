package com.mustplay.moviejournal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mustplay.moviejournal.ui.HistoryFragment;
import com.mustplay.moviejournal.ui.SearchFragment;
import com.mustplay.moviejournal.ui.BookmarksFragment;
import com.mustplay.moviejournal.ui.WatchFragment;
import com.mustplay.moviejournal.util.MovieStorage;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        init();
        openFragment(SearchFragment.newInstance());
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_search:
                            openFragment(SearchFragment.newInstance());
                            return true;
                        case R.id.navigation_watch:
                            openFragment(WatchFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_bookmarks:
                            openFragment(BookmarksFragment.newInstance());
                            return true;
                        case R.id.navigation_history:
                            openFragment(HistoryFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

    private void init(){
        MovieStorage.getInstance();
    }
}
