package com.example.soundboard;

import java.lang.reflect.Array;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

public class Note extends SoundboardActivity{

    private int soundID;
    private int delay;

    public Note(int soundID, int delay) {
        this.soundID = soundID;
        this.delay = delay;
    }


    public int getSoundID() {
        return soundID;
    }

    public void setSoundID(int soundID) {
        this.soundID = soundID;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}