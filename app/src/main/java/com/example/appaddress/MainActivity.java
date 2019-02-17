package com.example.appaddress;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private Button findButton;
    private Button deleteButton;
    private Button updateButton;
    private Button showAllButton;
    private SQLiteDatabase db;
    private static boolean runOnlyOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById((R.id.button));
        deleteButton = findViewById((R.id.button4));
        updateButton = findViewById((R.id.button3));
        findButton = findViewById(R.id.button5);
        showAllButton = findViewById(R.id.button2);
        if (!runOnlyOnce) init();
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button: //addPage
                intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.button4: //deletePage
                intent = new Intent(this, DeleteActivity.class);
                startActivity(intent);
                break;
//            case R.id.button5: //updatePage
//                intent = new Intent(this, Main4Activity.class);
//                startActivity(intent);
//                break;
            case R.id.button3: //findPage
                intent = new Intent(this, UpdateActivity.class);
                startActivity(intent);
                break;
            case R.id.button2: //ShowAllPage
                intent = new Intent(this, ShowAllActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void init(){
        runOnlyOnce = true;
        db = openOrCreateDatabase("UsersDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS contact_db(" +
                "name TEXT," +
                "address TEXT," +
                "email TEXT UNIQUE," +
                "phone TEXT);");
        db.execSQL("INSERT OR IGNORE INTO contact_db VALUES('alepis','athanasiou','j', '210992838');");
        db.execSQL("INSERT OR IGNORE INTO contact_db VALUES('Jason','Hrakleiou','Nikck@kalom.com', '210992839');");
        db.execSQL("INSERT OR IGNORE INTO contact_db VALUES('Jason','Hrakleiou','Nikck@kalom.com', '210992839');");
        db.execSQL("INSERT OR IGNORE INTO contact_db VALUES('Jason','Hrakleiou','Nikck@kalom.com', '210992839');");
        db.execSQL("INSERT OR IGNORE INTO contact_db VALUES('Jason','Hrakleiou','Nikck@kalom.com', '210992839');");
    }
}
