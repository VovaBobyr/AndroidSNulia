package ru.myrusakov.activitymethods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log("onCreate: Начинается выполнение activity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart: Подготовка к выводу на экран");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume: Переход в основной режим");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("onPause: Скрытие activity");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        log("onSaveInstanceState: Сохранение состояния activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log("onRestart: Возобновление activity после остановки");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("onDestroy: Полное удаление activity");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        log("onRestoreInstanceState: Восстановление состояния activity");
    }

    private void log(String s) {
        System.out.println(s);
    }
}
