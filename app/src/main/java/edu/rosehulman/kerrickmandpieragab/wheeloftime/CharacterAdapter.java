package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kerrickm on 1/23/2017.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Character> mCharacters = new ArrayList<Character>();
    private DatabaseReference database;
    private ArrayList<Character> mFavorites = new ArrayList<>();
    private ArrayList<Character> storedCharaters = new ArrayList<Character>();

    public CharacterAdapter(Context context, final String fragmentName) {
        mContext = context;

        database = FirebaseDatabase.getInstance().getReference();
        database.child("Characters").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> favNames = getNames();
                Iterator<DataSnapshot> d = dataSnapshot.getChildren().iterator();
                while (d.hasNext()) {
                    DataSnapshot next = d.next();
                    Character temp = new Character(next.child("name").getValue().toString(),
                                                   next.child("pronunciation").getValue().toString(),
                                                   next.child("description").getValue().toString(),
                                                   next.getKey());
                    if (MainActivity.favorites.contains(temp.getName())) {
                        temp.setFavorite(true);
                    }
                    mCharacters.add(temp);
                    storedCharaters.add(temp);
                }
                if (fragmentName.equals("SearchFragment")) {
                    notifyDataSetChanged();
                }
                else if (fragmentName.equals("FavoriteFragment")) {
                    for (int x = 0; x < MainActivity.favorites.size(); x++) {
                        for (int y = 0; y < storedCharaters.size(); y++) {
                            if (MainActivity.favorites.get(x).equals(storedCharaters.get(y).getName())) {
                                mFavorites.add(storedCharaters.get(y));
                            }
                        }
                    }
                    mCharacters = mFavorites;
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("CharacterAdapter", databaseError.getMessage());
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = mCharacters.get(position);
        holder.nameTextView.setText(character.getName());
        if (character.isFavorite()) {
            holder.favImageView.setImageResource(android.R.drawable.star_on);
        }
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public void addFavoriteToPage(Character ch) {
        notifyItemInserted(0);
        notifyItemRangeChanged(0, mFavorites.size());
        notifyItemInserted(0);
    }

    public void filter(String s) {
        mCharacters.clear();
        for (int x = 0; x < storedCharaters.size(); x++) {
            Character temp = storedCharaters.get(x);
            if (temp.getName().toUpperCase().contains(s.toUpperCase())) {
                mCharacters.add(temp);
            }
        }

        notifyDataSetChanged();
    }


    public void addFavorite(Character ch) {
        mFavorites.add(0, ch);
        addFavoriteToPage(ch);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private ImageView favImageView;

        public ViewHolder(View view) {
            super(view);

            nameTextView = (TextView)view.findViewById(R.id.character_name);
            favImageView = (ImageView)view.findViewById(R.id.favorite_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Character character = mCharacters.get(getAdapterPosition());

                    Intent detailIntent = new Intent(mContext, CharacterDetailActivity.class);
                    detailIntent.putExtra(CharacterDetailActivity.CHARACTER_NAME, character.getName());
                    detailIntent.putExtra(CharacterDetailActivity.CHARACTER_PRONUNCIATION, character.getPronunciation());
                    detailIntent.putExtra(CharacterDetailActivity.CHARACTER_DESCRIPTION, character.getDescription());
                    detailIntent.putExtra(CharacterDetailActivity.CHARACTER_KEY, character.getKey());
                    mContext.startActivity(detailIntent);
                }
            });
            favImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String selected = mCharacters.get(getAdapterPosition()).getName();
                    if (MainActivity.favorites.contains(selected)) {
                        MainActivity.favorites.remove(selected);
                        mCharacters.get(getAdapterPosition()).setFavorite(false);
                        favImageView.setImageResource(android.R.drawable.star_off);
                    }
                    else {
                        MainActivity.favorites.add(selected);
                        addFavorite(mCharacters.get(getAdapterPosition()));
                        favImageView.setImageResource(android.R.drawable.star_on);
                    }
                }
            });
        }
    }

    private Set<String> getNames() {
        Set<String> names = new HashSet<>();
        for (Character ch : mFavorites) {
            names.add(ch.getName());
        }
        return names;
    }
}
