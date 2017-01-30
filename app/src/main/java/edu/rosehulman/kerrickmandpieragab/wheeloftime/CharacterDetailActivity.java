package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CharacterDetailActivity extends AppCompatActivity {

    public static String CHARACTER_DESCRIPTION = "CHARACTER_DESCRIPTION";
    public static String CHARACTER_NAME = "CHARACTER_NAME";
    public static String CHARACTER_PRONUNCIATION = "CHARACTER_PRONUNCIATION";
    TextView mName;
    TextView mDescription;
    TextView mPronunciation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        mName = (TextView)findViewById(R.id.character_name);
        mDescription = (TextView)findViewById(R.id.character_description);
        mPronunciation = (TextView)findViewById(R.id.character_pronunciation);

        Intent intent = getIntent();
        mName.setText(intent.getStringExtra(CHARACTER_NAME));
        mDescription.setText(intent.getStringExtra(CHARACTER_DESCRIPTION));
        mPronunciation.setText(intent.getStringExtra(CHARACTER_PRONUNCIATION));

    }
}
