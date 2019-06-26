package ru.myrusakov.dataexchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            Person p = (Person) args.getSerializable(Person.class.getSimpleName());
            TextView tv = findViewById(R.id.tv);
            tv.setText(p.toString() + " " + args.getString("Example"));
        }
    }
}
