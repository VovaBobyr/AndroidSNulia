package ru.myrusakov.myaudio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer player;
    private Button start, pause, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        player = MediaPlayer.create(this, R.raw.music);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopPlayer();
            }
        });

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        System.out.println(current + " " + max);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, max, 0);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.start: {
                startPlayer();
                break;
            }
            case R.id.pause: {
                pausePlayer();
                break;
            }
            case R.id.stop: {
                stopPlayer();
                break;
            }
        }
    }

    private void startPlayer() {
        player.start();
        start.setEnabled(false);
        pause.setEnabled(true);
        stop.setEnabled(true);
    }

    private void pausePlayer() {
        player.pause();
        start.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(true);
    }

    private void stopPlayer() {
        player.stop();
        start.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(false);
        try {
            player.prepare();
            player.seekTo(0);
        } catch (Throwable t) {}
    }

    public void onDestroy() {
        super.onDestroy();
        if (player.isPlaying()) stopPlayer();
    }

}
