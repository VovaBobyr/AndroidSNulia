package ru.myrusakov.readsms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readSMS();
    }

    private void readSMS() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, 1);
            return;
        }
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/"), null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                /*System.out.println(cursor.getString(cursor.getColumnIndex("body")));
                System.out.println(cursor.getString(cursor.getColumnIndex("address")));
                System.out.println(cursor.getString(cursor.getColumnIndex("date")));*/
                //Telephony.TextBasedSmsColumns.MESSAGE_TYPE_SENT
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    System.out.println(cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
            } while (cursor.moveToNext());
        }
        else {
            System.out.println("Нет SMS");
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) readSMS();
            else System.out.println("Пользователь не разрешил читать SMS из приложения!");
        }
    }

}
