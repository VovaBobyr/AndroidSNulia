package ru.myrusakov.myservice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent service = new Intent(this, LogService.class);
        switch(view.getId()) {
            case R.id.start: {
                startService(service);
                break;
            }
            case R.id.stop: {
                stopService(service);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                String CHANNEL_ID = "my_channel";
                int NOTIFICATION_ID = 1;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Мои уведомления", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationChannel.setDescription("Описание канала");
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.GREEN);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("Информация")
                        .setContentText("Сервис остановлен")
                        .setVibrate(new long[]{0, 2000, 1100, 1000})
                        .setSmallIcon(R.mipmap.ic_launcher);
                Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
                builder.setContentIntent(pIntent);

                notificationManager.notify(NOTIFICATION_ID, builder.build());
                break;
            }
        }
    }
}
