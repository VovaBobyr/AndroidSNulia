package ru.myrusakov.myedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        editText.setText("abc@mail.ru");

        editText.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        editText.setTextSize(30);

        System.out.println(editText.getText());
    }
}
