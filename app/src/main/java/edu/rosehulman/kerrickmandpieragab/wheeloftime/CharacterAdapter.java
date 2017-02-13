package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kerrickm on 1/23/2017.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Character> mCharacters = new ArrayList<>();
    private FavoriteCallback mFavCallback;
    private RecyclerView mRecyclerView;

    public CharacterAdapter(Context context, RecyclerView view) {
        mContext = context;

        Character a = new Character("Perrin Aybara", "Pare-in Aye-barr-uh", "One of the three main characters. He is a blacksmith.");
        Character b = new Character("Mat Cauthon", "Mat Caw-thon", "One of the three main characters. He likes gambling and women.");
        Character c = new Character("Rand Al'Thor", "Rand al-thore", "The mainest of main characters. He is good at magic and swords.");

        mCharacters.add(a);
        mCharacters.add(b);
        mCharacters.add(c);

        mRecyclerView = view;
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
        mRecyclerView.scrollToPosition(0);
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
        mRecyclerView.scrollToPosition(0);
    }

    public interface FavoriteCallback {
        void addFavorite(Character ch);
        ArrayList<Character> getFavorites();
    }
}
