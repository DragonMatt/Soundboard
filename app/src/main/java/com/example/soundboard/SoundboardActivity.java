package com.example.soundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SoundboardActivity extends AppCompatActivity implements View.OnClickListener {

    public Button buttonA;
    public Button buttonB;
    public Button buttonBb;
    public Button buttonC;
    public Button buttonCs;
    public Button buttonD;
    public Button buttonDs;
    public Button buttonE;
    public Button buttonF;
    public Button buttonFs;
    public Button buttonG;
    public Button buttonGs;
    public Button buttonScale;
    public Button buttonSong1;
    public Button buttonSong2;
    public SoundPool soundPool;
    public boolean isSoundPoolLoaded = false;
    public int aNote;
    public int bNote;
    public int bbNote;
    public int cNote;
    public int csNote;
    public int dNote;
    public int dsNote;
    public int eNote;
    public int fNote;
    public int fsNote;
    public int gNote;
    public int gsNote;
    public Map<Integer, Integer> noteMap;
    private int[] scale;
    private Button buttonRecord;
    private boolean toggled;
    private Button buttonPlay;
    private ArrayList<Integer> recordedNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);
        wireWidgets();
        initializeSoundPool();
        setListeners();
    }

    private void wireWidgets() {
        buttonA = findViewById(R.id.button_main_A);
        buttonB = findViewById(R.id.button_main_B);
        buttonBb = findViewById(R.id.button_main_Bb);
        buttonC = findViewById(R.id.button_main_C);
        buttonCs = findViewById(R.id.button_main_Cs);
        buttonD = findViewById(R.id.button_main_D);
        buttonDs = findViewById(R.id.button_main_Ds);
        buttonE = findViewById(R.id.button_main_E);
        buttonF = findViewById(R.id.button_main_F);
        buttonFs = findViewById(R.id.button_main_Fs);
        buttonG = findViewById(R.id.button_main_G);
        buttonGs = findViewById(R.id.button_main_Gs);
        buttonScale = findViewById(R.id.button_main_scale);
        buttonSong1 = findViewById(R.id.button_main_song1);
        buttonSong2 = findViewById(R.id.button_main_song2);
        buttonRecord = findViewById(R.id.button_main_record);
        buttonPlay = findViewById(R.id.button_main_play);
    }

    private void initializeSoundPool() {
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

        aNote = soundPool.load(this, R.raw.scalea, 1);
        bNote = soundPool.load(this, R.raw.scaleb, 1);
        bbNote = soundPool.load(this, R.raw.scalebb, 1);
        cNote = soundPool.load(this, R.raw.scalec, 1);
        csNote = soundPool.load(this, R.raw.scalecs, 1);
        dNote = soundPool.load(this, R.raw.scaled, 1);
        dsNote = soundPool.load(this, R.raw.scaleds, 1);
        eNote = soundPool.load(this, R.raw.scalee, 1);
        fNote = soundPool.load(this, R.raw.scalef, 1);
        fsNote = soundPool.load(this, R.raw.scalefs, 1);
        gNote = soundPool.load(this, R.raw.scaleg, 1);
        gsNote = soundPool.load(this, R.raw.scalegs, 1);
        scale = new int[]{aNote, bNote, bbNote, cNote, csNote, dNote, dsNote, eNote, fNote, fsNote, gNote, gsNote};

        noteMap = new HashMap<>();
        noteMap.put (buttonA.getId(), aNote);
        noteMap.put (buttonB.getId(), bNote);
        noteMap.put (buttonBb.getId(), bbNote);
        noteMap.put (buttonC.getId(), cNote);
        noteMap.put (buttonCs.getId(), csNote);
        noteMap.put (buttonD.getId(), dNote);
        noteMap.put (buttonDs.getId(), dsNote);
        noteMap.put (buttonE.getId(), eNote);
        noteMap.put (buttonF.getId(), fNote);
        noteMap.put (buttonFs.getId(), fsNote);
        noteMap.put (buttonG.getId(), gNote);
        noteMap.put (buttonGs.getId(), gsNote);
    }

    public void delay(int millisDelay) {
        try {
            Thread.sleep(millisDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {
//        buttonA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                soundPool.play(aNote, 1, 1, 1, 0, 1);
//            }
//        });
//
//        buttonB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                soundPool.play(bNote, 1, 1, 1, 0, 1);
//            }
//        });
//
//        buttonC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                soundPool.play(cNote, 1, 1, 1, 0, 1);
//            }
//        });

        KeyboardListener keyboardListener = new KeyboardListener();
        buttonA.setOnClickListener(keyboardListener);
        buttonB.setOnClickListener(keyboardListener);
        buttonBb.setOnClickListener(keyboardListener);
        buttonC.setOnClickListener(keyboardListener);
        buttonCs.setOnClickListener(keyboardListener);
        buttonD.setOnClickListener(keyboardListener);
        buttonDs.setOnClickListener(keyboardListener);
        buttonE.setOnClickListener(keyboardListener);
        buttonF.setOnClickListener(keyboardListener);
        buttonFs.setOnClickListener(keyboardListener);
        buttonG.setOnClickListener(keyboardListener);
        buttonGs.setOnClickListener(keyboardListener);

        buttonScale.setOnClickListener(this);
        buttonSong1.setOnClickListener(this);
        buttonSong2.setOnClickListener(this);

        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!toggled)
                {
                    recordedNotes = new ArrayList<>();

                    buttonRecord.setText(getString(R.string.Stop));
                    toggled = true;
                }
                else {
                    buttonRecord.setText(getString(R.string.Record));
                    toggled = false;
                }
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int note : recordedNotes) {
                    soundPool.play(note, 1, 1, 1, 0, 1);
                    delay(250);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_main_scale : {
                Toast.makeText(this, "Scale", Toast.LENGTH_SHORT).show();
                for(int note : scale) {
                    soundPool.play(note, 1, 1, 1, 0, 1);
                    delay(250);
                }
                break;
            }
            case R.id.button_main_song1: {
                Toast.makeText(this, "Song 1", Toast.LENGTH_SHORT).show();
                soundPool.play(aNote, 1, 1, 1, 0, 1);
                delay(300);
                soundPool.play(bNote, 1, 1, 1, 0, 1);
                delay(300);
                soundPool.play(bbNote, 1, 1, 1, 0, 1);
                delay(300);
                soundPool.play(cNote, 1, 1, 1, 0, 1);
                break;
            }
            case R.id.button_main_song2: {
                Toast.makeText(this, "Song 2", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.button_main_record: {
                Toast.makeText(this, "Playing recorded notes", Toast.LENGTH_SHORT).show();
                break;
            }
        }
     }

    private class KeyboardListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // read from my map--looking up which button was pressed and then play the associated note
            int songId = noteMap.get(view.getId());
            if(songId != 0) {
                soundPool.play(songId, 1, 1, 1, 0, 1);
                if(toggled) {
                    recordedNotes.add(songId);
                }
            }
        }
    }
}