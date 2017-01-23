package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.provider.MediaStore;

/**
 * Created by pieragab on 1/23/2017.
 */

public class Character {
    private String mName;
    private String mPronunciation;
    private String mDescription;
    private MediaStore.Audio mAudio;

    public Character() {
    }

    public String getmName() {

        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPronunciation() {
        return mPronunciation;
    }

    public void setmPronunciation(String mPronunciation) {
        this.mPronunciation = mPronunciation;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public MediaStore.Audio getmAudio() {
        return mAudio;
    }

    public void setmAudio(MediaStore.Audio mAudio) {
        this.mAudio = mAudio;
    }
}
