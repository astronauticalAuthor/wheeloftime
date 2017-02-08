package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class HomeActivity extends AppCompatActivity implements Parcelable {

    private DatabaseReference database;
    private NewsAdapter newsAdapter;

    Intent searchIntent;
    Intent favoriteIntent;

    public HomeActivity() {
    }

    protected HomeActivity(Parcel in) {
        searchIntent = in.readParcelable(Intent.class.getClassLoader());
        favoriteIntent = in.readParcelable(Intent.class.getClassLoader());
    }

    public static final Creator<HomeActivity> CREATOR = new Creator<HomeActivity>() {
        @Override
        public HomeActivity createFromParcel(Parcel in) {
            return new HomeActivity(in);
        }

        @Override
        public HomeActivity[] newArray(int size) {
            return new HomeActivity[size];
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.news_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance().getReference();
        database.child("HomeEvents").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newsAdapter = new NewsAdapter((Map<String, String>)dataSnapshot.getValue());
                recyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("HomeActivity", databaseError.getMessage());
            }
        });

//        searchIntent = new Intent(this, SearchActivity.class);
//        favoriteIntent = new Intent(this, FavoriteActivity.class);
//        searchIntent.putExtra(getString(R.string.HOME_STRING), getIntent());
//        searchIntent.putExtra(getString(R.string.FAVORITE_STRING), favoriteIntent);
//        favoriteIntent.putExtra(getString(R.string.HOME_STRING), getIntent());
//        favoriteIntent.putExtra(getString(R.string.SEARCH_STRING), searchIntent);
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
                Log.d("already in home", "Clicked action_home");
                return true;

            case R.id.action_search:
                searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                Log.d("TTT", "Clicked action_search");
                return true;

            case R.id.action_favorites:
                favoriteIntent = new Intent(this,FavoriteActivity.class);
                startActivity(favoriteIntent);
                Log.d("TTT", "Clicked action_favorites");
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
        parcel.writeParcelable(searchIntent, i);
        parcel.writeParcelable(favoriteIntent, i);
    }
}
