package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by pieragab on 1/23/2017.
 */

public class SearchFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {
    private HomeFragment.Callback mCallback;
    private CharacterAdapter mAdapter;
    RecyclerView view;
    EditText searchText;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout lay = (LinearLayout) inflater.inflate(R.layout.search_page, container, false);

        view = (RecyclerView) lay.findViewById(R.id.search_recycler);
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        view.setHasFixedSize(true);
        mAdapter = new CharacterAdapter(view.getContext(), "SearchFragment");
        view.setAdapter(mAdapter);

        searchText = (EditText)lay.findViewById(R.id.search_bar);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mAdapter.filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return lay;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_home:
                mCallback.homeClick(this);
                return true;
            case R.id.action_search:
                mCallback.searchClick(this);
                return true;

            case R.id.action_favorites:
                mCallback.favoriteClick(this);
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
