package ru.myrusakov.myhardlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Алексей", 20));
        persons.add(new Person("Екатерина", 25));
        persons.add(new Person("Александр", 30));
        persons.add(new Person("Евгений", 30));
        persons.add(new Person("Татьяна", 30));

        ListView lv = findViewById(R.id.lv);
        PersonAdapter adapter = new PersonAdapter(this, R.layout.item, persons);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Person p = (Person) adapterView.getItemAtPosition(position);
                System.out.println("Вы выбрали: " + p.getName() + ", " + p.getAge() + " лет");
            }
        });
    }
}
