package com.example.appaddress;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class FindActivity extends AppCompatActivity {

    private Button findByEmailButton;
    private Button findByNameButton;
    private EditText searchByEmail;
    private EditText searchByName;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        findByEmailButton = findViewById(R.id.button9);
        findByNameButton = findViewById(R.id.button10);
        searchByEmail = findViewById(R.id.editText8);
        searchByName = findViewById(R.id.editText9);
    }

    public void onClick(View v) {
        if (R.id.button9 == v.getId()) {
            queryExcecutor("email", true);
        } else {
            queryExcecutor("name", false);
        }
    }

    private void queryExcecutor(String input, boolean isEmail) {
        Cursor cursor;
        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        if (isEmail) {
            cursor = db.rawQuery(
                    "SELECT * FROM contact_db WHERE " + input.toLowerCase() +
                            " LIKE '%" + searchByEmail.getText().toString().toLowerCase() + "%';", null);
        } else {
            cursor = db.rawQuery(
                    "SELECT * FROM contact_db WHERE " + input.toLowerCase() +
                            " LIKE '%" + searchByName.getText().toString().toLowerCase() + "%';", null);
        }
        if (cursor.getCount() == 0) {
            showMessage("Error", "No records found...");
        } else {
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()) {
                buffer.append("User name: ").append(cursor.getString(0)).append("\n");
                buffer.append("User address: ").append(cursor.getString(1)).append("\n");
                buffer.append("User email: ").append(cursor.getString(2)).append("\n");
                buffer.append("User tel: ").append(cursor.getString(3)).append("\n");
                buffer.append("--------------------------------\n");
            }
            cursor.close();
            showMessage("Records", buffer.toString());
        }
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
