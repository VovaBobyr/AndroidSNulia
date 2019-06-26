package ru.myrusakov.basedraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyDraw(this));
    }

    private class MyDraw extends View {

        public MyDraw(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.YELLOW);
        }

    }

}
