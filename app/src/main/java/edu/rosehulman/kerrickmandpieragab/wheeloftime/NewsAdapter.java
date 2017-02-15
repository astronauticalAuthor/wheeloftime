package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kerrickm on 1/30/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> descriptions = new ArrayList<String>();
    private DatabaseReference database;
//    Context mContext;

    public NewsAdapter() {
        database = FirebaseDatabase.getInstance().getReference();
        database.child("HomeEvents").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> map = (Map<String, String>)dataSnapshot.getValue();

                String[] keys = map.keySet().toArray(new String[map.keySet().size()]);
                for (int x = 0; x < keys.length; x++) {
                    titles.add(keys[x]);
                    descriptions.add(map.get(keys[x]));
                }

                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("NewsAdapter", databaseError.getMessage());
            }
        });
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        String title = titles.get(position);
        String desc = descriptions.get(position);

        holder.titleText.setText(title);
        holder.descriptionText.setText(desc);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleText;
        private TextView descriptionText;

        public ViewHolder(View view) {
            super(view);

            titleText = (TextView)view.findViewById(R.id.news_title);
            descriptionText = (TextView)view.findViewById(R.id.news_description);
        }
    }
}
