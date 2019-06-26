package ru.myrusakov.callphone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void call(View view) {
        callPhone();
    }

    private void callPhone() {
        EditText phone = findViewById(R.id.phone);
        String call = "tel:" + phone.getText().toString();
        //startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(call)));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(call)));
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) callPhone();
            else System.out.println("Пользователь не разрешил совершать звонки из приложения!");
        }
    }

}
