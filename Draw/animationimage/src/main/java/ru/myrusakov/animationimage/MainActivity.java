package ru.myrusakov.animationimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = findViewById(R.id.imageView);
        Animation imageAnim = AnimationUtils.loadAnimation(this, R.anim.image_animation);
        iv.startAnimation(imageAnim);

        ImageView clock = findViewById(R.id.clock);
        Animation clockAnim = AnimationUtils.loadAnimation(this, R.anim.clock_animation);
        clock.startAnimation(clockAnim);
    }
}
