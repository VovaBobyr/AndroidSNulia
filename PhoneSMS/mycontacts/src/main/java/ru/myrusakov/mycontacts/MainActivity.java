package ru.myrusakov.mycontacts;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addContact();
        readContacts();
    }

    private void addContact() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_CONTACTS}, 2);
            return;
        }
        String name = "Алексей";
        String phone = "1234567890";

        ContentValues cv = new ContentValues();
        Uri uri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, cv);

        long id = ContentUris.parseId(uri);

        cv.clear();
        cv.put(ContactsContract.Data.RAW_CONTACT_ID, id);
        cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        cv.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, cv);

        cv.clear();
        cv.put(ContactsContract.Data.RAW_CONTACT_ID, id);
        cv.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, cv);
    }


    private void readContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            return;
        }
        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                for (int i = 0; i < c.getColumnCount(); i++) {
                    //System.out.println(c.getColumnName(i) + ": " + c.getString(i));
                }

                /*Cursor cNew = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[]{c.getString(c.getColumnIndex("_id"))}, null);
                if (cNew.moveToFirst()) {
                    do {
                        for (int i = 0; i < cNew.getColumnCount(); i++) {
                            System.out.println(cNew.getColumnName(i) + ": " + cNew.getString(i));
                        }
                    } while (cNew.moveToNext());
                }
                */
                Cursor cNew = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{c.getString(c.getColumnIndex("_id"))}, null);
                if (cNew.moveToFirst()) {
                    do {
                        for (int i = 0; i < cNew.getColumnCount(); i++) {
                            System.out.println(cNew.getColumnName(i) + ": " + cNew.getString(i));
                        }
                    } while (cNew.moveToNext());
                }
            } while (c.moveToNext());
        }
        else {
            System.out.println("Нет контактов");
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) readContacts();
            else System.out.println("Пользователь не разрешил читать свои контакты!");
        }
        else if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) addContact();
            else System.out.println("Пользователь не разрешил редактировать свои контакты!");
        }
    }
}
