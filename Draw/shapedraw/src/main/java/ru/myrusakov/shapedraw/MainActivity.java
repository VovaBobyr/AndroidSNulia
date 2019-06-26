package ru.myrusakov.shapedraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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

        protected void onDraw(Canvas c) {
            c.drawRGB(180, 180, 180);

            Paint p = new Paint();
            p.setColor(Color.YELLOW);
            p.setStrokeWidth(10);
            c.drawPoint(100, 100, p);
            c.drawLine(50, 50, 500, 800, p);
            c.drawRect(400, 400, 550, 650, p);
            p.setColor(Color.BLUE);
            c.drawCircle(650, 750, 100, p);

            RectF rect = new RectF(300, 100, 700, 450);
            p.setStyle(Paint.Style.STROKE);
            c.drawRoundRect(rect, 30, 30, p);

            p.setColor(Color.RED);
            c.drawOval(rect, p);

            rect.offsetTo(50, 800);
            p.setStyle(Paint.Style.FILL);
            c.drawRect(rect, p);

            p.setColor(Color.BLACK);
            p.setTextSize(50);
            c.drawText("Hello World!", 600, 500, p);

            p.setTextAlign(Paint.Align.CENTER);
            c.drawText("Hello World!", 600, 550, p);

            p.setTextAlign(Paint.Align.RIGHT);
            c.drawText("Hello World!", 600, 600, p);
        }

    }
}
