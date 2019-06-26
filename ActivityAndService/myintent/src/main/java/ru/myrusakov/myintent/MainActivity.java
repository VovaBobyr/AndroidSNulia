package ru.myrusakov.myintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SECOND_ACTIVITY = "ru.myrusakov.MY_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (findViewById(R.id.button)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Intent intent = new Intent(this, SecondActivity.class);
        //startActivity(intent);
        Intent intent = new Intent(SECOND_ACTIVITY);
        startActivity(intent);
    }
}
