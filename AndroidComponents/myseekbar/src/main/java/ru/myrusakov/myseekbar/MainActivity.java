package ru.myrusakov.myseekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar sb = findViewById(R.id.seekBar);
        //sb.setProgress(50);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int progress = seekBar.getProgress();
                System.out.println(progress);
                TextView tv = findViewById(R.id.tv);
                tv.setText("Прогресс: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                System.out.println("Нажат");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("Отпущен");
            }
        });
    }
}
