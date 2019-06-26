package ru.myrusakov.mysoundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SoundPool sp;
    private int soundIDShot;
    private int soundIDBeep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundIDShot = sp.load(this, R.raw.shot, 1);
        soundIDBeep = sp.load(this, R.raw.beep, 1);

        Button shot = findViewById(R.id.shot);
        Button beep = findViewById(R.id.beep);
        shot.setOnClickListener(this);
        beep.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shot: {
                play(soundIDShot);
                break;
            }
            case R.id.beep: {
                play(soundIDBeep);
                break;
            }
        }
    }

    private void play(int soundID) {
        sp.play(soundID, 1, 1, 0, 0, 1);
    }
}
