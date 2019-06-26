package ru.myrusakov.myvideo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView player;
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

        player = findViewById(R.id.player);
        //player.setVideoPath("https://files.myrusakov.ru/pm/webmoney.mp4");
        player.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
        MediaController mc = new MediaController(this);
        player.setMediaController(mc);
        mc.setMediaPlayer(player);
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
        player.stopPlayback();
        player.resume();
        start.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(false);
    }

}
