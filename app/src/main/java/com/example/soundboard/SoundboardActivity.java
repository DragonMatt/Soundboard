package com.example.soundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundboardActivity extends AppCompatActivity {

    private Button A;
    private Button B;
    private Button C;
    private SoundPool soundPool;
    private boolean isSoundPoolLoaded = false;
    private int aNote;
    private int bNote;
    private int cNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);

        // Set the hardware buttons to control the music
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        // Load the sound
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                isSoundPoolLoaded = true;
            }
        });

        wireWidgets();
        initializeSoundPool();
        setListeners();
    }

    private void wireWidgets() {
        A = findViewById(R.id.button_main_A);
        B = findViewById(R.id.button_main_B);
        C = findViewById(R.id.button_main_C);
    }

    private void initializeSoundPool() {
        aNote = soundPool.load(this, R.raw.scalea, 1);
        cNote = soundPool.load(this, R.raw.scalec, 1);
        bNote = soundPool.load(this, R.raw.scaleb, 1);
    }

    private void setListeners() {
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(aNote, 1, 1, 0, 0, 1);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(bNote, 1, 1, 0, 0, 1);
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(cNote, 1, 1, 0, 0, 1);
            }
        });
    }
}