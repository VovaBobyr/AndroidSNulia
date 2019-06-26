package ru.myrusakov.myimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = findViewById(R.id.imageView);
                String tag = imageView.getTag().toString();
                switch(tag) {
                    case "smile_1": {
                        imageView.setImageResource(R.drawable.smile_2);
                        imageView.setTag("smile_2");
                        break;
                    }
                    case "smile_2": {
                        imageView.setImageResource(R.drawable.smile_1);
                        imageView.setTag("smile_1");
                        break;
                    }
                }
            }
        });
    }
}
