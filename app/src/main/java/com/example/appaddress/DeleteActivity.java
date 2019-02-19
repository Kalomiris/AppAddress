package com.example.appaddress;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    private Button deleteButton;
    private EditText email;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        email = findViewById(R.id.editText);
        deleteButton = findViewById(R.id.button7);
    }

    public void onClick(View view) {
        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE, null);
        db.execSQL("DELETE FROM contact_db WHERE email = '" + email.getText().toString() + "'");
        Toast.makeText(this,"Deleted Successfully!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MainActivity.class));
    }
}
