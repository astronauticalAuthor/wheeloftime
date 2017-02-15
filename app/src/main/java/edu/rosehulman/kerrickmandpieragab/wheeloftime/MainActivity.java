package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity implements HomeFragment.Callback {
    Fragment homeFragment;
    Fragment searchFragment;
    Fragment favoriteFragment;
    NewsAdapter mNewsAdapter;
    public static ArrayList<String> favorites;
    private String FAVORITES = "FAVORITES";
    private String PREFS = "PREFS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        Set<String> s = new TreeSet<String>();
        s = prefs.getStringSet(FAVORITES, s);
        favorites = new ArrayList<String>();
        if (!s.isEmpty()) {
            Object[] arr = s.toArray();
            for (int x = 0; x < arr.length; x++) {
                String temp = arr[x].toString();
                favorites.add(temp);
            }
        }

        homeFragment = new HomeFragment();
        favoriteFragment = new FavoriteFragment();
        searchFragment = new SearchFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment, homeFragment, "Home");
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

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Set<String> temp = new HashSet<String>(favorites);
        editor.putStringSet(FAVORITES, temp);
        editor.commit();
    }
}
