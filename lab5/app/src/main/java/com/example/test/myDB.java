package com.example.test;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class myDB extends SQLiteOpenHelper
{
    private static final String DB_name = "my_db";
    private static final int DB_version = 1;

    public myDB(Context context)
    {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE users (user_name TEXT PRIMARY KEY, user_pas TEXT);");
        db.execSQL("INSERT INTO users (user_name, user_pas) VALUES ('admin', 'admin')");
        db.execSQL("INSERT INTO users (user_name, user_pas) VALUES ('user1', 'pas1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
