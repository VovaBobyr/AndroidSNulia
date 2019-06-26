package ru.myrusakov.myradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButton1: {
                        System.out.println("1 радио-кнопка");
                        break;
                    }
                    case R.id.radioButton2: {
                        System.out.println("2 радио-кнопка");
                        break;
                    }
                    case R.id.radioButton3: {
                        System.out.println("3 радио-кнопка");
                        break;
                    }
                }
                String result = "Выбран вариант: " + ((RadioButton) findViewById(i)).getText();
                System.out.println(result);
            }
        });
    }
}
