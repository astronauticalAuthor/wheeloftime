package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class HomeFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private NewsAdapter newsAdapter;
    private Callback mCallback;

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsAdapter = new NewsAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.activity_home, container, false);

        view.setLayoutManager(new LinearLayoutManager(getContext()));
        view.setHasFixedSize(true);
        newsAdapter = new NewsAdapter();
        view.setAdapter(newsAdapter);

        return view;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_home:
                Log.d("already in home", "Clicked action_home");
                return true;
            case R.id.action_search:
                mCallback.searchClick(this);
                Log.d("TTT", "Clicked action_search");
                return true;

            case R.id.action_favorites:
                mCallback.favoriteClick(this);
                Log.d("TTT", "Clicked action_favorites");
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {

    }

    public interface Callback {
        void favoriteClick(Fragment frag);
        void searchClick(Fragment frag);
        void homeClick(Fragment frag);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}
