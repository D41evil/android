package com.example.test;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import java.lang.reflect.Array;

public class Main  extends AppCompatActivity
{
    SQLiteDatabase db;
    Cursor cursor;
    SimpleCursorAdapter adapter;
    myDB helper;

    String[] users = new String[2];
    String[] pas = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        helper = new myDB(getApplicationContext());
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from users", null);

        int i = 0;
        while (cursor.moveToNext())
        {
            users[i] = cursor.getString(0);
            pas[i] = cursor.getString(1);
            i++;
        }
        //((EditText)(findViewById(R.id.pt1))).setText(users[0] + pas[0] + users[1] + pas[1]);
    }
    public void Enter(View view)
    {
        int pos = -1;
        for (int i = 0; i < users.length; i++)
        {
            if (users[i].equals(((EditText) (findViewById(R.id.pt1))).getText().toString()))
            {
                pos = i;
            }
        }
        //((EditText)(findViewById(R.id.pt2))).setText(((EditText)(findViewById(R.id.pt1))).getText().toString());
        if (pos != -1 && ((EditText) (findViewById(R.id.pt1))).getText().toString().equals(users[pos]) && ((EditText) (findViewById(R.id.pt2))).getText().toString().equals(pas[pos]))
        {
            AlertDialog.Builder alt_builder = new AlertDialog.Builder(this);
            alt_builder.setMessage("Подключиться?")
                    .setCancelable(false)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Main.this, Second.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alert = alt_builder.create();
            alert.setTitle("Подтверждение входа");
            alert.show();
        }
    }
}
