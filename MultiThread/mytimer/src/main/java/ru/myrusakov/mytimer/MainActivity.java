package ru.myrusakov.mytimer;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        MyTimer timer = new MyTimer(5000, 1000);
        timer.start();
    }

    private class MyTimer extends CountDownTimer {

        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisFinished) {
            int s = (int) millisFinished / 1000;
            if (s < 10) tv.setText("00:0" + s);
            else tv.setText("00:" + s);
        }

        @Override
        public void onFinish() {
            System.out.println("Таймер закончился!");
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {0, 2000, 1000};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //v.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
                v.vibrate(VibrationEffect.createWaveform(pattern, 1));
            }
            else {
                //v.vibrate(2000);
                v.vibrate(pattern, 1);
            }
            //v.cancel();
        }
    }
}
