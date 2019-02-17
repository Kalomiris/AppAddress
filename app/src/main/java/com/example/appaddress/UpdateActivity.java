package com.example.appaddress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private Button updateButton;
    private EditText email, tel;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        email = findViewById(R.id.editText3);
        updateButton = findViewById(R.id.button8);
        tel = findViewById(R.id.editText7);
    }

    public void onClick(View view) {
        if (!hasEmail()) {
            Toast.makeText(this,"Enter an email!", Toast.LENGTH_SHORT).show();
        }
        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        db.execSQL("UPDATE contact_db SET phone = '" + tel.getText().toString() + "'" +
                " WHERE email = '" + email.getText().toString() + "'");
        startActivity(new Intent(this, MainActivity.class));
        Toast.makeText(this,"Updated Successfully!", Toast.LENGTH_SHORT).show();
    }

    private boolean hasEmail(){
        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("SELECT email FROM contact_db WHERE email = '" + email.getText().toString() + "';", null);
        cursor.moveToNext();
        if (cursor.getString(0) != null){
            return true;
        }
        return false;
    }
}
