package com.example.emanuel.registroempleaos.data_access;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.emanuel.registroempleaos.utils.DataBaseConstants;

public class SQLiteConnection extends SQLiteOpenHelper {

    public SQLiteConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseConstants.CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DataBaseConstants.DROP_TABLE_EMPLOYEE);
        onCreate(sqLiteDatabase);
    }
}
