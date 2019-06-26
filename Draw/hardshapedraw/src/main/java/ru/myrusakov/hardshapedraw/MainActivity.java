package ru.myrusakov.hardshapedraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

        private Paint p;
        private Path path;

        public MyDraw(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(5);
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.YELLOW);
            p.setTextSize(80);
            path = new Path();
        }

        protected void onDraw(Canvas c) {
            c.drawRGB(180, 180, 180);
            path.reset();

            path.moveTo(50, 50);
            path.lineTo(400, 100);
            path.lineTo(400, 450);
            path.lineTo(50, 50);
            path.addCircle(200, 200, 50, Path.Direction.CW);
            path.addPath(new MyPath());
            c.drawPath(path, p);

            MyPathForText pathText = new MyPathForText();
            c.drawPath(pathText, p);

            c.drawTextOnPath("Привет, мир!", pathText, 0, 70, p);
        }

    }

    private class MyPath extends Path {

        public MyPath() {
            reset();
            moveTo(200, 300);
            lineTo(400, 500);
            lineTo(500, 800);
            lineTo(200, 800);
            quadTo(400, 500, 450, 800);
        }
    }

    private class MyPathForText extends Path {

        public MyPathForText() {
            reset();
            moveTo(500, 300);
            rLineTo(240, 170);
            rLineTo(150, -170);
        }

    }

}