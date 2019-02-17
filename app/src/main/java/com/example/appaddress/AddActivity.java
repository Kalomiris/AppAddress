package com.example.appaddress;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    Button saveButton, mainMenuButton;
    EditText name, address, email, phone;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.editText2);
        address = findViewById(R.id.editText6);
        email = findViewById(R.id.editText4);
        phone = findViewById(R.id.editText5);
        saveButton = findViewById(R.id.button6);
        mainMenuButton = findViewById(R.id.button4);
    }

    public void onClick(View v) {
        List<String> contact = new ArrayList<>();
        contact.add(name.getText().toString().trim());
        contact.add(address.getText().toString().trim());
        contact.add(email.getText().toString().trim());
        contact.add(phone.getText().toString().trim());
        Intent intent;
        switch (v.getId()) {
            case R.id.button6: //save
                if (save(contact)) {
                    intent = new Intent(this, MainActivity.class);
                    intent.putExtra("mymessage", "Saved Successfully!");
                    startActivity(intent);
                }
                break;
            case R.id.button4: //main menu
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean save(List<String> contact) {
        if (contact != null) {
            db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
            db.execSQL("INSERT OR IGNORE INTO contact_db VALUES" +
                    "(" + "'" + contact.get(0) + "'" + "," +
                    "'" + contact.get(1) +
                    "'" + "," + "'" + contact.get(2) + "'" + "," + "'" +
                    contact.get(3) + "'" + ");");
            return true;
        }
        return false;
    }


}
