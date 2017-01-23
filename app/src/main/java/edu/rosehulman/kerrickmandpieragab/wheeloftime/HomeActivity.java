package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                Log.d("TTT", "Clicked action_home");
                return true;

            case R.id.action_search:
                Log.d("TTT", "Clicked action_search");
                return true;

            case R.id.action_favorites:
                Log.d("TTT", "Clicked action_favorites");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
