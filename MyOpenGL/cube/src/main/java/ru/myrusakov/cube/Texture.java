package ru.myrusakov.cube;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import static android.opengl.GLES20.GL_LINEAR;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_CUBE_MAP;
import static android.opengl.GLES20.GL_TEXTURE_CUBE_MAP_NEGATIVE_X;
import static android.opengl.GLES20.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y;
import static android.opengl.GLES20.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z;
import static android.opengl.GLES20.GL_TEXTURE_CUBE_MAP_POSITIVE_X;
import static android.opengl.GLES20.GL_TEXTURE_CUBE_MAP_POSITIVE_Y;
import static android.opengl.GLES20.GL_TEXTURE_CUBE_MAP_POSITIVE_Z;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glTexParameteri;

public class Texture {

    public static int loadTexture(Context context, int resourceID) {
        final int[] textureIDs = new int[1];
        glGenTextures(1, textureIDs, 0);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceID, options);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_CUBE_MAP, textureIDs[0]);
        glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        GLUtils.texImage2D(GL_TEXTURE_CUBE_MAP_NEGATIVE_X, 0, bitmap, 0);
        GLUtils.texImage2D(GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, 0, bitmap, 0);
        GLUtils.texImage2D(GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, 0, bitmap, 0);

        GLUtils.texImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_X, 0, bitmap, 0);
        GLUtils.texImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_Y, 0, bitmap, 0);
        GLUtils.texImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_Z, 0, bitmap, 0);

        bitmap.recycle();
        glBindTexture(GL_TEXTURE_CUBE_MAP, 0);

        return textureIDs[0];
    }

}