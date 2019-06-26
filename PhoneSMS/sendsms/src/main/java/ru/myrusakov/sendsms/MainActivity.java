package ru.myrusakov.sendsms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view) {
        sendSMS();
    }

    public void sendSMS() {
        EditText phone = findViewById(R.id.phone);
        EditText message = findViewById(R.id.message);
        String to = "smsto:" + phone.getText().toString();
        String body = message.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(to));
        intent.putExtra("sms_body", body);
        //startActivity(intent);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
            return;
        }
        SmsManager.getDefault().sendTextMessage(phone.getText().toString(), null, body, null, null);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) sendSMS();
            else System.out.println("Пользователь не разрешил отправлять SMS из приложения!");
        }
    }

}
