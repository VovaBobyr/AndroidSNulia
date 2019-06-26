package ru.myrusakov.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.TimeUnit;

public class LogService extends Service {

    private boolean stop;

    public LogService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    public void onCreate() {
        super.onCreate();
        log("Старт сервиса");
        stop = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(true) {
                    if (stop) break;
                    log("Прошло " + i + " с.");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }).start();
    }

    public void onDestroy() {
        super.onDestroy();
        stop = true;
        log("Остановка сервиса");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("Запуск");
        return super.onStartCommand(intent, flags, startId);
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
