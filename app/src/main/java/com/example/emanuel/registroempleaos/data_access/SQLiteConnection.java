package com.example.emanuel.registroempleaos.data_access;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConnection extends SQLiteOpenHelper {

    private final String CREATE_TABLE = "CREATE TABLE EMPLOYEE (id INTEGER, name TEXT, age INTEGER)";
    private final String DROP_TABLE = "DROP TABLE IF EXISTS EMPLOYEE"

    public SQLiteConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
