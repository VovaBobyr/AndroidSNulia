package ru.myrusakov.cube;

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
import static android.opengl.GLES20.GL_TEXTURE_CUBE_MAP;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.GL_UNSIGNED_BYTE;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glDrawElements;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private int programID;
    private int uMatrixLocation;
    private int aPositionLocation;
    private int uTextureLocation;
    private int texture;
    private FloatBuffer vertexData;
    private ByteBuffer cubeBuffer;
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
        uTextureLocation = glGetUniformLocation(programID, "u_Texture");
        uMatrixLocation = glGetUniformLocation(programID, "u_Matrix");

        texture = Texture.loadTexture(context, R.drawable.wood);

        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, 3, GL_FLOAT, false, 0, vertexData);
        glEnableVertexAttribArray(aPositionLocation);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_CUBE_MAP, texture);

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

        count += SPEED;
        if (count >= 1) count = 0;

        float posX = (float) Math.cos(count * 2 * Math.PI) * 2f;
        float posY = 1.5f;
        float posZ = (float) Math.sin(count * 2 * Math.PI) * 2f;

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
        float s = 0.9f;
        float[] vertices = {
                -s, s, s, // 0Верхний левый угол
                -s, -s, s, // 1Нижний левый угол
                s, -s, s, // 2Нижний правый угол
                s, s, s, // 3Верхний правый угол
                -s, s, -s, // 4Верхний левый угол
                -s, -s, -s, // 5Нижний левый угол
                s, -s, -s, // 6Нижний правый угол
                s, s, -s // 7Верхний правый угол
        };

        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);

        cubeBuffer = ByteBuffer.allocateDirect(36);
        cubeBuffer.put(new byte[] {
                //Ближняя
                0, 1, 2,
                0, 3, 2,

                //Левая
                4, 0, 1,
                4, 5, 1,

                //Правая
                7, 6, 2,
                7, 3, 2,

                //Дальняя
                4, 5, 6,
                4, 7, 6,

                //Верхняя
                4, 0, 3,
                4, 7, 3,

                //Нижняя
                5, 1, 2,
                5, 6, 2
        });
        cubeBuffer.position(0);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        createMatrixCamera();
        createMatrixResult();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_BYTE, cubeBuffer);

    }

}