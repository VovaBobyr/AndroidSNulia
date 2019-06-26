package ru.myrusakov.opengl3d;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glView = new GLSurfaceView(this);
        glView.setEGLContextClientVersion(2);
        glView.setRenderer(new OpenGLRenderer(this));
        setContentView(glView);
    }

    protected void onPause() {
        super.onPause();
        glView.onPause();
    }

    protected void onResume() {
        super.onResume();
        glView.onResume();
    }


}
