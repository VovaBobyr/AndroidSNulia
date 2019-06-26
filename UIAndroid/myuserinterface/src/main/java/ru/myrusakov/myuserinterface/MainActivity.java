package ru.myrusakov.myuserinterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //System.out.println(R.string.app_name);
        //System.out.println(getResources().getString(R.string.app_name));

        System.out.println(getResources().getString(R.string.app_name));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText et1 = findViewById(R.id.number1);
        EditText et2 = findViewById(R.id.number2);
        String number1 = et1.getText().toString();
        String number2 = et2.getText().toString();
        System.out.println(number1);
        System.out.println(number2);
        Intent intent = new Intent(this, BackActivity.class);
        intent.putExtra("NUMBER_1", number1);
        intent.putExtra("NUMBER_2", number2);
        startActivity(intent);
    }
}
