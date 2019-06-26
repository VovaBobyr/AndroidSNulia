package ru.myrusakov.readwritefile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = findViewById(R.id.save);
        Button load = findViewById(R.id.load);
        Button delete = findViewById(R.id.delete);

        save.setOnClickListener(this);
        load.setOnClickListener(this);
        delete.setOnClickListener(this);

        String[] files = fileList();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save: {
                save();
                break;
            }
            case R.id.load: {
                load();
                break;
            }
            case R.id.delete: {
                delete();
                break;
            }
        }
    }

    private void save() {
        String text = ((EditText) findViewById(R.id.text)).getText().toString();
        try {
            FileOutputStream out = openFileOutput(FILE_NAME, MODE_APPEND);
            out.write(text.getBytes());
            out.close();
            showToast("В файл добавлены данные!");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Ошибка при записи в файл!");
        }
    }

    private void load() {
        try {
            FileInputStream in = openFileInput(FILE_NAME);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
            String s = new String(buffer);
            ((TextView) findViewById(R.id.tv)).setText(s);
        } catch (Exception e) {
            e.printStackTrace();
            ((TextView) findViewById(R.id.tv)).setText("");
            showToast("Ошибка при чтении файла!");
        }

    }

    private void delete() {
        if (deleteFile(FILE_NAME)) showToast("Файл успешно удалён!");
        else showToast("Ошибка при удалении файла!");
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
