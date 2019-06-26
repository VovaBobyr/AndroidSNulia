package ru.myrusakov.mymatrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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

        private Paint p;
        private Path path;
        private Matrix m;

        public MyDraw(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(5);
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.YELLOW);
            path = new Path();
            m = new Matrix();
        }

        protected void onDraw(Canvas c) {
            c.drawRGB(180, 180, 180);
            path.reset();
            path.addRect(100, 100, 300, 300, Path.Direction.CW);
            c.drawPath(path, p);

            m.reset();
            m.setTranslate(200, 200);
            path.transform(m);
            p.setColor(Color.RED);
            c.drawPath(path, p);

            path.addCircle(350, 350, 30, Path.Direction.CW);
            m.reset();
            m.setScale(1.5f, 2.5f);
            path.transform(m);
            c.drawPath(path, p);

            path.reset();
            path.moveTo(400, 900);
            path.lineTo(700, 900);
            p.setColor(Color.MAGENTA);
            c.drawPath(path, p);

            m.reset();
            m.setRotate(40, 700, 900);
            path.transform(m);
            p.setColor(Color.GREEN);
            c.drawPath(path, p);

            path.reset();
            path.addCircle(900, 900, 100, Path.Direction.CW);
            c.drawPath(path, p);

            m.reset();
            m.setTranslate(0, -250);
            path.transform(m);
            c.drawPath(path, p);
            m.setSkew(0.2f, 0.5f, 900, 700);
            path.transform(m);
            c.drawPath(path, p);

            RectF rectSource = new RectF(200, 400, 350, 550);
            RectF rectDist = new RectF(900, 100, 1000, 500);

            path.reset();
            path.addOval(rectSource, Path.Direction.CW);
            p.setColor(Color.CYAN);
            c.drawPath(path, p);

            c.drawRect(rectDist, p);
            m.reset();
            //m.setRectToRect(rectSource, rectDist, Matrix.ScaleToFit.START);
            //m.setRectToRect(rectSource, rectDist, Matrix.ScaleToFit.END);
            //m.setRectToRect(rectSource, rectDist, Matrix.ScaleToFit.CENTER);
            m.setRectToRect(rectSource, rectDist, Matrix.ScaleToFit.FILL);
            path.transform(m);
            c.drawPath(path, p);
        }

    }
}
