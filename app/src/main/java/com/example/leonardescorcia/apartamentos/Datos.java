package com.example.leonardescorcia.apartamentos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Leonard Escorcia on 19/05/2017.
 */

public class Datos {

    public static ArrayList<Apartamento> traerApartamento(Context contexto) {

        ArrayList<Apartamento> apartamentos = new ArrayList<>();

        //Declarar variables
        SQLiteDatabase db;
        Apartamento ap;
        String sql,nomenclatura, piso, tamano, sombra, balcon, precio;;

        //Abrir conexión de lectura
        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
        db = aux.getReadableDatabase();

        //Cursor
        sql = "select * from Apartamentos";
        Cursor c = db.rawQuery(sql, null);

        //Recorrido de cursor
        if(c.moveToFirst()){
            do{
                nomenclatura=c.getString(0);
                piso=c.getString(1);
                tamano=c.getString(2);
                sombra=c.getString(3);
                balcon=c.getString(4);
                precio=c.getString(5);
                ap = new Apartamento(nomenclatura, piso, tamano, sombra, balcon, precio);
                apartamentos.add(ap);
            }
            while (c.moveToNext());
        }
        db.close();

        return apartamentos;
    }

    public static Apartamento buscarApartamentos(Context contexto, String nom, String pis){

        //Declarar variables
        SQLiteDatabase db;
        Apartamento ap=null;
        String sql, nomenclatura, piso, tamano, sombra, balcon, precio;

        //Abrir conexión de lectura
        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
        db = aux.getReadableDatabase();

        sql="Select * from Apartamentos WHERE"+"nomenclatura ='" + nom + "'" + ", piso ='" + pis +"'";
        Cursor c=db.rawQuery(sql,null);

        //Recorrido de cursor
        if(c.moveToFirst()) {
                nomenclatura = c.getString(0);
                piso = c.getString(1);
                tamano = c.getString(2);
                sombra = c.getString(3);
                balcon = c.getString(4);
                precio = c.getString(5);
                ap = new Apartamento(nomenclatura, piso, tamano, sombra, balcon, precio);
            }
            db.close();
            return ap;
    }



}
