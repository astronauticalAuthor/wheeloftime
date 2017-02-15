package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pieragab on 1/23/2017.
 */

public class FavoriteFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {
    private CharacterAdapter mAdapter;
    private HomeFragment.Callback mCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.favorites_page, container, false);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        view.setHasFixedSize(true);
        mAdapter = new CharacterAdapter(view.getContext(), "FavoriteFragment");
        view.setAdapter(mAdapter);
        return view;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_home:
                mCallback.homeClick(this);
                Log.d("TTT", "Clicked action_home");
                return true;
            case R.id.action_search:
                mCallback.searchClick(this);
                Log.d("TTT", "Clicked action_search");
                return true;

            case R.id.action_favorites:
                mCallback.favoriteClick(this);
                Log.d("already in favorite", "Clicked action_favorites");
                return true;
        }
        return false;
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragment.Callback) {
            mCallback = (HomeFragment.Callback) context;
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
