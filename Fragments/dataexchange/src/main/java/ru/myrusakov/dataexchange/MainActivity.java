package ru.myrusakov.dataexchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements ButtonFragment.FragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void update(String data) {
        TextFragment fragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.textFragment);
        fragment.setTV(data);
    }
}
