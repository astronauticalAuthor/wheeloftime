package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CharacterDetailActivity extends AppCompatActivity {

    public static String CHARACTER_DESCRIPTION = "CHARACTER_DESCRIPTION";
    public static String CHARACTER_NAME = "CHARACTER_NAME";
    public static String CHARACTER_PRONUNCIATION = "CHARACTER_PRONUNCIATION";
    public static String CHARACTER_KEY = "CHARACTER_KEY";
    TextView mName;
    TextView mDescription;
    TextView mPronunciation;
    ImageButton audio;
    final FirebaseStorage storage = FirebaseStorage.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        mName = (TextView)findViewById(R.id.character_name);
        mDescription = (TextView)findViewById(R.id.character_description);
        mPronunciation = (TextView)findViewById(R.id.character_pronunciation);
        audio = (ImageButton)findViewById(R.id.audio_button);

        Intent intent = getIntent();

        mName.setText(intent.getStringExtra(CHARACTER_NAME));
        mDescription.setText(intent.getStringExtra(CHARACTER_DESCRIPTION));
        mPronunciation.setText(intent.getStringExtra(CHARACTER_PRONUNCIATION));
        final String mKey = intent.getStringExtra(CHARACTER_KEY);

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference sr = storage.getReferenceFromUrl("gs://wheel-of-time.appspot.com/" + mKey + ".wav");
                sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        MediaPlayer mp = MediaPlayer.create(CharacterDetailActivity.this, uri);
                        mp.start();
                    }
                });
            }
        });

    }
}
