package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kerrickm on 1/23/2017.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Character> mCharacters = new ArrayList<>();
    private FavoriteCallback mFavCallback;
    private DatabaseReference database;

    public CharacterAdapter(Context context) {
        mContext = context;

        Character a = new Character("Perrin Aybara", "Pare-in Aye-barr-uh", "One of the three main characters. He is a blacksmith.");
        Character b = new Character("Mat Cauthon", "Mat Caw-thon", "One of the three main characters. He likes gambling and women.");
        Character c = new Character("Rand Al'Thor", "Rand al-thore", "The mainest of main characters. He is good at magic and swords.");

//        mCharacters.add(a);
//        mCharacters.add(b);
//        mCharacters.add(c);

        database = FirebaseDatabase.getInstance().getReference();
        database.child("Characters").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> d = dataSnapshot.getChildren().iterator();
                while (d.hasNext()) {
                    DataSnapshot next = d.next();
                    Log.d("TTT", next.getKey());
                    Character temp = new Character(next.child("name").getValue() + "", "XXX", next.child("description").getValue() + "");
                    mCharacters.add(temp);
//                    Iterator<DataSnapshot> characteristics = next.getChildren().iterator();
//                    while (characteristics.hasNext()) {
//                        Log.d("TTT", characteristics.next().getKey());
//                    }

//                    Log.d("TTT", d.next().getKey());
//                    d.next().
                }
                notifyDataSetChanged();
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
//            holder.favImageView.setImageIcon(mContext.getResources().getDrawable(R.icon.star_full));
        }
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public void addFavoriteToPage(Character ch) {
        notifyItemInserted(0);
        notifyItemRangeChanged(0, mFavCallback.getFavorites().size());
        notifyItemInserted(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
                    mContext.startActivity(detailIntent);
                }
            });
            favImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFavCallback.addFavorite(mCharacters.get(getAdapterPosition()));
                }
            });
        }
    }

    public void addCharacter(Character ch) {
        notifyItemInserted(0);
        notifyItemRangeChanged(0, mCharacters.size());
        notifyItemInserted(0);
    }

    public interface FavoriteCallback {
        void addFavorite(Character ch);
        ArrayList<Character> getFavorites();
    }
}
