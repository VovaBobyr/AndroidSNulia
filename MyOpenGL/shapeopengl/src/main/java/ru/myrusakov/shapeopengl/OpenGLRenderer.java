package ru.myrusakov.shapeopengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_LINE_LOOP;
import static android.opengl.GLES20.GL_LINE_STRIP;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glLineWidth;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private int programID;
    private int uColorLocation;
    private int aPositionLocation;
    private FloatBuffer vertexData;

    public OpenGLRenderer(Context context) {
        this.context = context;
        prepareVertexData();
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        glClearColor(0, 0, 0, 1);
        int vertexID = Shader.createShader(context, GL_VERTEX_SHADER, R.raw.vertex_shader);
        int pixelID = Shader.createShader(context, GL_FRAGMENT_SHADER, R.raw.pixel_shader);
        programID = Shader.createProgram(vertexID, pixelID);
        glUseProgram(programID);
        uColorLocation = glGetUniformLocation(programID, "u_Color");
        glUniform4f(uColorLocation, 0.5f, 1f, 0.5f, 1);

        aPositionLocation = glGetAttribLocation(programID, "a_Position");
        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0, vertexData);
        glEnableVertexAttribArray(aPositionLocation);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        glViewport(0, 0, width, height);
    }

    private void prepareVertexData() {
        float x1 = -0.5f;
        float y1 = 0;
        float x2 = 0;
        float y2 = 0.5f;
        float x3 = 0.5f;
        float y3 = 0;
        float[] vertices = {
                x1, y1,
                x2, y2,
                x3, y3,
                -0.9f, 0.9f,
                -0.8f, 0.8f,
                0.8f, 0.8f,
                -0.8f, -0.8f,
                0.8f, -0.8f,
                -0.4f, 0.7f,
                -0.4f, -0.7f,
                0.4f, 0.7f,
                0.4f, -0.7f
        };
        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        glClear(GL_COLOR_BUFFER_BIT);
        glDrawArrays(GL_TRIANGLES, 0, 3);
        glDrawArrays(GL_POINTS, 3, 1);
        glLineWidth(10);
        //glDrawArrays(GL_LINES, 4, 4);
        //glDrawArrays(GL_LINE_STRIP, 4, 4);
        glDrawArrays(GL_LINE_LOOP, 4, 4);
        //glDrawArrays(GL_TRIANGLE_STRIP, 8, 4);
        glDrawArrays(GL_TRIANGLE_FAN, 8, 4);
    }

}
