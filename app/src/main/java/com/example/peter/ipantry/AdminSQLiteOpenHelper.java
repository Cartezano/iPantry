package com.example.peter.ipantry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Peter on 22-06-2017.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table productos(" +
                "codigoProducto int primary key," +
                "nombreProducto text," +
                "marcaProducto text," +
                "cantidadProducto text," +
                "fechaVencimientoProducto text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

    }
}
