package ru.myrusakov.coloropengl;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileLoad {

    public static String readTextFromFile(Context context, int resourceID) {
        try {
            InputStream input = context.getResources().openRawResource(resourceID);
            BufferedReader reader = new BufferedReader(new InputStreamReader((input)));
            String result = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                result += line + "\r\n";
            }
            reader.close();
            return result;
        } catch (IOException e) {}
        return "";
    }

}
