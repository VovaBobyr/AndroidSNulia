package ru.myrusakov.mycheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    ArrayList<CheckBox> checkboxes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout = findViewById(R.id.layout);
        int count = layout.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = layout.getChildAt(i);
            if (v instanceof CheckBox) {
                ((CheckBox) v).setOnCheckedChangeListener(this);
                checkboxes.add((CheckBox) v);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) System.out.println("Мы купили: " + compoundButton.getText());
        else System.out.println("Мы не купили: " + compoundButton.getText());
        String result = "Результат покупки: ";
        for (CheckBox c : checkboxes) {
            if (c.isChecked()) result += c.getText() + ", ";
        }
        if (!result.equals("Результат покупки: ")) result = result.substring(0, result.length() - 2);
        System.out.println(result);
    }
}
