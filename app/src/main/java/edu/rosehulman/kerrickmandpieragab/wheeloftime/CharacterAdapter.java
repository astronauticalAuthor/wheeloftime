package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kerrickm on 1/23/2017.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Character> mCharacters = new ArrayList<Character>();

    public CharacterAdapter(Context context) {
        mContext = context;

        Character a = new Character("Perrin Aybara", "Pare-in Aye-barr-uh", "One of the three main characters. He is a blacksmith.");
        Character b = new Character("Mat Cauthon", "Mat Caw-thon", "One of the three main characters. He likes gambling and women.");
        Character c = new Character("Rand Al'Thor", "Rand al-thore", "The mainest of main characters. He is good at magic and swords.");

        mCharacters.add(a);
        mCharacters.add(b);
        mCharacters.add(c);
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
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;

        public ViewHolder(View view) {
            super(view);

            nameTextView = (TextView)view.findViewById(R.id.character_name);
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
        }
    }
}
