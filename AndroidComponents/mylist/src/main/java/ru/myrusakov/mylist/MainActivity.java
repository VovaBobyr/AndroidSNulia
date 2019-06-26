package ru.myrusakov.mylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> beverages;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beverages = new ArrayList();
        beverages.add("Сок");
        beverages.add("Вода");
        beverages.add("Газировка");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, beverages);

        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "" + Math.random();
                adapter.add(s);
                adapter.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                adapter.remove(adapter.getItem(position));
                adapter.notifyDataSetChanged();
            }
        });
    }
}
