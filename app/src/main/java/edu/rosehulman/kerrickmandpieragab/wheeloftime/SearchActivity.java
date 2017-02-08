package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by pieragab on 1/23/2017.
 */

public class SearchActivity extends AppCompatActivity implements Parcelable {
    Intent homeIntent;
    Intent favoriteIntent;

    private CharacterAdapter mAdapter;

    public SearchActivity() {
    }

    protected SearchActivity(Parcel in) {
        homeIntent = in.readParcelable(Intent.class.getClassLoader());
        favoriteIntent = in.readParcelable(Intent.class.getClassLoader());
    }

    public static final Creator<SearchActivity> CREATOR = new Creator<SearchActivity>() {
        @Override
        public SearchActivity createFromParcel(Parcel in) {
            return new SearchActivity(in);
        }

        @Override
        public SearchActivity[] newArray(int size) {
            return new SearchActivity[size];
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
        mAdapter = new CharacterAdapter(this);
        recyclerView.setAdapter(mAdapter);

        EditText searchText = (EditText) findViewById(R.id.search_bar);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("Search bar", "Text changed");
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

//        Bundle extras = getIntent().getExtras();
//        homeIntent = extras.getParcelable(getString(R.string.HOME_STRING));
//        favoriteIntent = extras.getParcelable(getString(R.string.FAVORITE_STRING));
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
                Log.d("already in search", "Clicked action_search");
                return true;

            case R.id.action_favorites:
                favoriteIntent = new Intent(this, FavoriteActivity.class);
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
        parcel.writeParcelable(homeIntent, i);
        parcel.writeParcelable(favoriteIntent, i);
    }
}
