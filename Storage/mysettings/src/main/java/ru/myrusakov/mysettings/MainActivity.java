package ru.myrusakov.mysettings;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String SETTINGS_NAME = "settings";
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.button)).setOnClickListener(this);
        settings = getSharedPreferences(SETTINGS_NAME, MODE_PRIVATE);

        Map<String, ?> map = settings.getAll();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        /*SharedPreferences.Editor editor = settings.edit();
        editor.remove("important");
        editor.apply();*/

        String s = settings.getString("important", "Hello");
        ((TextView) findViewById(R.id.tv)).setText(s);
    }

    @Override
    public void onClick(View view) {
        String s = "" + Math.random();
        ((TextView) findViewById(R.id.tv)).setText(s);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("important", s);
        editor.apply();
    }
}
