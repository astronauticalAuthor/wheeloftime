package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by pieragab on 1/23/2017.
 */

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


//        ViewPager vp = (ViewPager)findViewById(R.id.pager);
//        vp.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), this));
//
//        TabLayout tl = (TabLayout)findViewById(R.id.sliding_tabs);
//        tl.setupWithViewPager(vp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent homeIntent = new Intent(this, HomeActivity.class);
                startActivity(homeIntent);
                Log.d("TTT", "Clicked action_home");
                return true;

            case R.id.action_search:
                Log.d("already in search", "Clicked action_search");
                return true;

            case R.id.action_favorites:
                Intent favoriteIntent = new Intent(this, FavoriteActivity.class);
                startActivity(favoriteIntent);
                Log.d("TTT", "Clicked action_favorites");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
