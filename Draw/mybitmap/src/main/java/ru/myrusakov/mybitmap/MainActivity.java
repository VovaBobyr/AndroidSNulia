package ru.myrusakov.mybitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
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

        private Bitmap bm;

        public MyDraw(Context context) {
            super(context);
            bm = BitmapFactory.decodeResource(getResources(), R.drawable.tree);
            System.out.println("Ширина = " + bm.getWidth());
            System.out.println("Высота = " + bm.getHeight());
            System.out.println("Размер = " + bm.getByteCount());
        }

        protected void onDraw(Canvas c) {
            Paint p = new Paint();
            //c.drawBitmap(bm, 0, 0, p);

            Matrix m = new Matrix();
            m.setScale(0.1f, 0.1f);
            m.postTranslate(150, 0);
            c.drawBitmap(bm, m, p);

            bm = Bitmap.createBitmap(bm, 1500, 1500, 500, 500);
            c.drawBitmap(bm, 200, 600, p);
        }

    }

}
