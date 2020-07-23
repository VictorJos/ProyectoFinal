package com.example.proyectofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {



    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"FB", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumnos(codigo int primary key , nombre Text, apellido Text, telefono Text )");
          db.execSQL("create table notas( codigo int primary key,nombre Text, apellido Text,cedula Text, nota1 real, nota2 real,nota3 real,total real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList llenar_lv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM alumnos";
        Cursor registros = database.rawQuery(q, null);
        if(registros.moveToFirst()){
            do {
                lista.add(registros.getString(1)+"  " + registros.getString(2));
            }while (registros.moveToNext());
        }

        return lista;

    }

    public ArrayList llenar_no(){
        ArrayList<String> listanota = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM alumnos";
        Cursor registros = database.rawQuery(q, null);
        if(registros.moveToFirst()){
            do {
                listanota.add(registros.getString(1)+"  " + registros.getString(2));
            }while (registros.moveToNext());
        }

        return listanota;

    }

}
