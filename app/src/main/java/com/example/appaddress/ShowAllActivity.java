package com.example.appaddress;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShowAllActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("SELECT * FROM contact_db ORDER BY name ASC",null);
        if (cursor.getCount()==0)
            showMessage("Error","No records found...");
        else {
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                buffer.append("User name: "+cursor.getString(0)+"\n");
                buffer.append("User address: "+cursor.getString(1)+"\n");
                buffer.append("User email: "+cursor.getString(2)+"\n");
                buffer.append("User tel: "+cursor.getString(3)+"\n");
                buffer.append("--------------------------------\n");
            }
            cursor.close();
            showMessage("Records",buffer.toString());
        }
    }

    private void showMessage(String records, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(records);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
