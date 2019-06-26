package ru.myrusakov.mytexture;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_TEST;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private int programID;
    private int uMatrixLocation;
    private int aPositionLocation;
    private int aTextureLocation;
    private int uTextureLocation;
    private int texture;
    private FloatBuffer vertexData;
    private float[] matrix = new float[16];
    private float[] matrixCamera = new float[16];
    private float[] matrixTransform = new float[16];
    private float[] matrixResult = new float[16];
    private double count = 0;
    private final double SPEED = 0.01;

    public OpenGLRenderer(Context context) {
        this.context = context;
        prepareVertexData();
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        glClearColor(0, 0, 0, 1);
        glEnable(GL_DEPTH_TEST);
        int vertexID = Shader.createShader(context, GL_VERTEX_SHADER, R.raw.vertex_shader);
        int pixelID = Shader.createShader(context, GL_FRAGMENT_SHADER, R.raw.pixel_shader);
        programID = Shader.createProgram(vertexID, pixelID);
        glUseProgram(programID);

        aPositionLocation = glGetAttribLocation(programID, "a_Position");
        aTextureLocation = glGetAttribLocation(programID, "a_Texture");
        uTextureLocation = glGetUniformLocation(programID, "u_Texture");
        uMatrixLocation = glGetUniformLocation(programID, "u_Matrix");

        texture = Texture.loadTexture(context, R.drawable.wood);

        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, 3, GL_FLOAT, false, 20, vertexData);
        glEnableVertexAttribArray(aPositionLocation);

        vertexData.position(3);
        glVertexAttribPointer(aTextureLocation, 2, GL_FLOAT, false, 20, vertexData);
        glEnableVertexAttribArray(aTextureLocation);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture);

        glUniform1i(uTextureLocation, 0);

        createMatrixCamera();
        Matrix.setIdentityM(matrixTransform, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        glViewport(0, 0, width, height);
        createMatrix(width, height);
        createMatrixResult();
    }

    private void createMatrix(int width, int height) {
        float left = -1.0f;
        float right = 1.0f;
        float bottom = -1.0f;
        float top = 1.0f;
        float near = 1.0f;
        float far = 10.0f;
        if (width > height) {
            float ratio = (float) width / height;
            left *= ratio;
            right *= ratio;
        }
        else {
            float ratio = (float) height / width;
            bottom *= ratio;
            top *= ratio;
        }
        Matrix.frustumM(matrix, 0, left, right, bottom, top, near, far);
    }

    private void createMatrixCamera() {

        float posX = 0;
        float posY = 0;
        float posZ = 5f;

        float cenX = 0;
        float cenY = 0;
        float cenZ = 0;

        float upX = 0;
        float upY = 1;
        float upZ = 0;

        Matrix.setLookAtM(matrixCamera, 0, posX, posY, posZ, cenX, cenY, cenZ, upX, upY, upZ);
    }

    private void createMatrixResult() {

        Matrix.multiplyMM(matrixResult, 0, matrixCamera, 0, matrixTransform, 0);
        Matrix.multiplyMM(matrixResult, 0, matrix, 0, matrixResult, 0);
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrixResult, 0);
    }

    private void prepareVertexData() {
        float[] vertices = {
                -0.8f, 0.8f, 3.9f, 0, 0,
                -0.8f, -0.8f, 3.9f, 0, 1,
                0.8f, 0.8f, 3.9f, 1, 0,
                0.8f, -0.8f, 3.9f, 1, 1
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

    }

}
