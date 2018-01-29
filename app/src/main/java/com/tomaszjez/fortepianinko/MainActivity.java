package com.tomaszjez.fortepianinko;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button doNote, reNote, miNote, faNote, solNote, laNote, siNote, do2Note, recordButton, playButton;
    private SoundPool soundPool;
    private int sound_do, sound_re, sound_mi, sound_fa, sound_sol, sound_la, sound_si, sound_do2;

    private CountDownTimer cdt;
    private long timer;
    private boolean isRecording = false;
    private Map<Long, String> recorded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiateButtons();
        instantiateSound();
    }

    public void recordStuff() {
        cdt = new CountDownTimer(5000, 50) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer = (millisUntilFinished/10)*10;
            }


            @Override
            public void onFinish() {
                isRecording = false;
                playButton.setEnabled(true);
                recordButton.setEnabled(true);
                recorded.isEmpty();
            }
        }.start();
    }

    public void startRecord(View v) {
        playButton.setEnabled(false);
        recordButton.setEnabled(false);
        recorded = new HashMap<>();
        isRecording = true;
        recordStuff();
    }

    public void playRecorded(View v) {
        recordButton.setEnabled(false);
        playButton.setEnabled(false);
        new CountDownTimer(5000, 1) {
            HashMap<Long, String> playback = new HashMap<>(recorded);
            @Override
            public void onTick(long millisUntilFinished) {
                Long mathMagic = (millisUntilFinished/10)*10;
                if (playback.containsKey(mathMagic)) {
                    playbeep(playback.get(mathMagic));
                    playback.remove(mathMagic);
                }
            }

            @Override
            public void onFinish() {
                recordButton.setEnabled(true);
                playButton.setEnabled(true);
            }
        }.start();
    }

    private void playbeep(String btnId)
    {
        switch (btnId) {
            case "do_but":
                playDo();
                break;
            case "re_but":
                playRe();
                break;
            case "mi_but":
                playMi();
                break;
            case "fa_but":
                playFa();
                break;
            case "sol_but":
                playSol();
                break;
            case "la_but":
                playLa();
                break;
            case "si_but":
                playSi();
                break;
            case "do2_but":
                playDo2();
                break;
        }
    }

    private void record(View v) {
        if (isRecording)
            recorded.put(timer, v.getResources().getResourceEntryName(v.getId()));
    }

    private void instantiateButtons() {
        doNote = (Button) findViewById(R.id.do_but);
        doNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playDo();
                record(view);
            }
        });
        reNote = (Button) findViewById(R.id.re_but);
        reNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playRe();
                record(view);
            }
        });
        miNote = (Button) findViewById(R.id.mi_but);
        miNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMi();
                record(view);
            }
        });
        faNote = (Button) findViewById(R.id.fa_but);
        faNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playFa();
                record(view);
            }
        });
        solNote = (Button) findViewById(R.id.sol_but);
        solNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSol();
                record(view);
            }
        });
        laNote = (Button) findViewById(R.id.la_but);
        laNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playLa();
                record(view);
            }
        });
        siNote = (Button) findViewById(R.id.si_but);
        siNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSi();
                record(view);
            }
        });
        do2Note = (Button) findViewById(R.id.do2_but);
        do2Note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playDo2();
                record(view);
            }
        });
        recordButton = (Button) findViewById(R.id.record);
        playButton = (Button) findViewById(R.id.play);
        playButton.setEnabled(false);
    }

    private void instantiateSound() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().setMaxStreams(5).build();
        } else {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        sound_do = soundPool.load(this, R.raw.do_note, 1);
        sound_re = soundPool.load(this, R.raw.re, 1);
        sound_mi = soundPool.load(this, R.raw.mi, 1);
        sound_fa = soundPool.load(this, R.raw.fa, 1);
        sound_sol = soundPool.load(this, R.raw.sol, 1);
        sound_la = soundPool.load(this, R.raw.la, 1);
        sound_si = soundPool.load(this, R.raw.si, 1);
        sound_do2 = soundPool.load(this, R.raw.do2_note, 1);
    }

    private void playDo() {
        soundPool.play(sound_do, 1, 1, 0, 0, 1);
    }

    private void playRe() {
        soundPool.play(sound_re, 1, 1, 0, 0, 1);
    }

    private void playMi() {
        soundPool.play(sound_mi, 1, 1, 0, 0, 1);
    }

    private void playFa() {
        soundPool.play(sound_fa, 1, 1, 0, 0, 1);
    }

    private void playSol() {
        soundPool.play(sound_sol, 1, 1, 0, 0, 1);
    }

    private void playLa() {
        soundPool.play(sound_la, 1, 1, 0, 0, 1);
    }

    private void playSi() {
        soundPool.play(sound_si, 1, 1, 0, 0, 1);
    }

    private void playDo2() {
        soundPool.play(sound_do2, 1, 1, 0, 0, 1);
    }
}
