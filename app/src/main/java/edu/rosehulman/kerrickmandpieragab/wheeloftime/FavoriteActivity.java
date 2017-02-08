package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by pieragab on 1/23/2017.
 */

public class FavoriteActivity extends AppCompatActivity implements Parcelable {
    Intent homeIntent;
    Intent searchIntent;

    public FavoriteActivity() {
    }

    protected FavoriteActivity(Parcel in) {
        homeIntent = in.readParcelable(Intent.class.getClassLoader());
        searchIntent = in.readParcelable(Intent.class.getClassLoader());
    }

    public static final Creator<FavoriteActivity> CREATOR = new Creator<FavoriteActivity>() {
        @Override
        public FavoriteActivity createFromParcel(Parcel in) {
            return new FavoriteActivity(in);
        }

        @Override
        public FavoriteActivity[] newArray(int size) {
            return new FavoriteActivity[size];
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_page);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);


//        ViewPager vp = (ViewPager)findViewById(R.id.pager);
//        vp.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), this));
//
//        TabLayout tl = (TabLayout)findViewById(R.id.sliding_tabs);
//        tl.setupWithViewPager(vp);

//        Bundle extras = getIntent().getExtras();
//        homeIntent = extras.getParcelable(getString(R.string.HOME_STRING));
//        searchIntent = extras.getParcelable(getString(R.string.SEARCH_STRING));
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
                homeIntent = new Intent(this, HomeActivity.class);
                startActivity(homeIntent);
                Log.d("TTT", "Clicked action_home");
                return true;

            case R.id.action_search:
                searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                Log.d("TTT", "Clicked action_search");
                return true;

            case R.id.action_favorites:
                Log.d("already in favorites", "Clicked action_favorites");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(homeIntent, i);
        parcel.writeParcelable(searchIntent, i);
    }
}
