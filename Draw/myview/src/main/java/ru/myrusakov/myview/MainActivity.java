package ru.myrusakov.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rl = findViewById(R.id.layout);
        MyView view = new MyView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 300);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        view.setLayoutParams(params);
        rl.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyView myView = (MyView) view;
                myView.setActive(!myView.getActive());
            }
        });
    }

    private class MyView extends View {

        private boolean active = false;

        public MyView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            Paint p = new Paint();
            Path path = new Path();
            RectF rect = new RectF(0, 0, getWidth(), getHeight());
            path.addOval(rect, Path.Direction.CW);
            if (active) p.setColor(Color.RED);
            else p.setColor(Color.GREEN);
            canvas.drawPath(path, p);
        }

        public boolean getActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
            invalidate();
        }

    }
}
