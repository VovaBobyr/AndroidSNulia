package ru.myrusakov.mytexture;

import android.content.Context;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;

public class Shader {

    public static int createProgram(int vertexShaderID, int pixelShaderID) {
        final int programID = glCreateProgram();
        glAttachShader(programID, vertexShaderID);
        glAttachShader(programID, pixelShaderID);
        glLinkProgram(programID);
        final int[] status = new int[1];
        glGetProgramiv(programID, GL_LINK_STATUS, status, 0);
        if (status[0] == 0) {
            glDeleteProgram(programID);
            return 0;
        }
        return programID;
    }

    public static int createShader(Context context, int type, int shaderResourceID) {
        String text = FileLoad.readTextFromFile(context, shaderResourceID);
        final int shaderID = glCreateShader(type);
        glShaderSource(shaderID, text);
        glCompileShader(shaderID);
        final int[] status = new int[1];
        glGetShaderiv(shaderID, GL_COMPILE_STATUS, status, 0);
        if (status[0] == 0) {
            glDeleteShader(shaderID);
            return 0;
        }
        return shaderID;
    }

}
