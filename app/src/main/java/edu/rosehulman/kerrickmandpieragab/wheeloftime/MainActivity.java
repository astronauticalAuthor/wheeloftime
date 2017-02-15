package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements HomeFragment.Callback {
    Fragment homeFragment;
    Fragment searchFragment;
    Fragment favoriteFragment;
    NewsAdapter mNewsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        homeFragment = new HomeFragment();
        favoriteFragment = new FavoriteFragment();
        searchFragment = new SearchFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment, homeFragment, "Home");
        //homeClick(homeFragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (id) {
            case R.id.action_home:
                ft.replace(R.id.fragment, homeFragment, "Home");
                ft.commit();

                return true;
            case R.id.action_search:
                ft.replace(R.id.fragment, searchFragment, "Search");
                ft.commit();

                return true;

            case R.id.action_favorites:
                ft.replace(R.id.fragment, favoriteFragment, "Favorites");
                ft.commit();
                return true;
        }
        return false;
    }

    @Override
    public void favoriteClick(Fragment frag) {

    }

    @Override
    public void searchClick(Fragment frag) {

    }

    @Override
    public void homeClick(Fragment frag) {

    }
}
