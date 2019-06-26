package ru.myrusakov.dataexchange;

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
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText name = findViewById(R.id.name);
        EditText age = findViewById(R.id.age);
        Person p = new Person(name.getText().toString(), Integer.parseInt(age.getText().toString()));

        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("Example", "Hello!!!");
        intent.putExtra(Person.class.getSimpleName(), p);
        startActivity(intent);
    }
}
