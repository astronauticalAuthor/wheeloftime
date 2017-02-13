package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.provider.MediaStore;

/**
 * Created by pieragab on 1/23/2017.
 */

public class Character {
    private String mName;
    private String mPronunciation;
    private String mDescription;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    private boolean isFavorite;
    private MediaStore.Audio mAudio;

    public Character(String name) {
        mName = name;
    }

    public Character(String name, String pronunciation, String description) {
        mName = name;
        mPronunciation = pronunciation;
        mDescription = description;
        isFavorite = false;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPronunciation() {
        return mPronunciation;
    }

    public void setPronunciation(String pronunciation) {
        mPronunciation = pronunciation;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public MediaStore.Audio getAudio() {
        return mAudio;
    }

    public void setmAudio(MediaStore.Audio audio) {
        mAudio = audio;
    }
}
