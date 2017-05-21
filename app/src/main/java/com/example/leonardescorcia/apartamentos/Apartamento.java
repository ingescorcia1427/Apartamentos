package com.example.leonardescorcia.apartamentos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Leonard Escorcia on 17/05/2017.
 */

public class Apartamento {
    String nomenclatura, piso, tamano, sombra, balcon, precio;

    public Apartamento(String nomenclatura, String piso, String tamano, String sombra, String balcon, String precio) {
        this.nomenclatura = nomenclatura;
        this.piso = piso;
        this.tamano = tamano;
        this.sombra = sombra;
        this.balcon = balcon;
        this.precio = precio;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public String getPiso() {
        return piso;
    }

    public String getTamano() {
        return tamano;
    }


    public String getSombra() {
        return sombra;
    }

    public String getBalcon() {
        return balcon;
    }

    public String getPrecio() {
        return precio;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }


    public void setSombra(String sombra) {
        this.sombra = sombra;
    }

    public void setBalcon(String balcon) {
        this.balcon = balcon;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }


    public void guardar(Context contexto){
        SQLiteDatabase db;
        String sql;

        //Abrir la conexión de la base de datos en modo escritura
        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
        db = aux.getWritableDatabase();

        //Insertar forma 1
        sql = "INSERT INTO Apartamentos values('"
                +this.getNomenclatura()+"','"
                +this.getPiso()+"','"
                +this.getTamano()+"','"
                +this.getSombra()+"','"
                +this.getBalcon()+"','"
                +this.getPrecio()+"')";

        db.execSQL(sql);

        //Cerrar conexión
        db.close();
    }

    public void modificar(Context contexto){
        //declarar las variables
        String sql;
        SQLiteDatabase db;

        //Abrir ña conexión de base de datos en modo escritura
        ApartamentosSQLiteOpenHelper aux=new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
        db=aux.getWritableDatabase();

        sql="UPDATE Apartamentos SET piso ='"+this.getPiso()
                +"',metros='"+this.getTamano()
                +"',precio='"+this.getPrecio()
                +"',sombra='"+this.getSombra()
                +"',balcon='"+this.getBalcon()
                +"'where nomenclatura='"+this.getNomenclatura()+"'";
        db.execSQL(sql);

        db.close();
    }

}
