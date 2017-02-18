package com.example.trjano.chupitos.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.trjano.chupitos.Chupito;
import com.example.trjano.chupitos.Tipo;

import java.util.ArrayList;

/**
 * Created by trjano on 14/02/17.
 */

public class ChupitosDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "chupitos.db";

    public ChupitosDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Es recomendable que la llave primaria sea BaseColumns._ID, ya que el framework de
     * Android usa esta referencia internamente en varios procesos.
      * @param db
     * Este método solo se ejecuta una vez, cuando se van a crear las tablas
     *
     * Este método es llamado automáticamente cuando creamos una instancia de la clase SQLiteOpenHelper.
     * En su interior establecemos la creación de las tablas y registros.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("aqui llegaaras antes de bd");
        db.execSQL("CREATE TABLE " + Tablas.TChupitos.TABLE_NAME + " ("
                + Tablas.TChupitos._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Tablas.TChupitos.NOMBRE + " TEXT NOT NULL,"
                + Tablas.TChupitos.TIPO + " TEXT NOT NULL,"
                + Tablas.TChupitos.ING1 + " TEXT NOT NULL,"
                + Tablas.TChupitos.ING2 + " TEXT,"
                + Tablas.TChupitos.ING3 + " TEXT,"
                + Tablas.TChupitos.DESC + " TEXT,"
                + "UNIQUE ("+ Tablas.TChupitos.NOMBRE+"));");

    }

   /** Este es ejecutado si se identificó que el usuario tiene una versión antigua de la
    base de datos.

    En su interior establecerás instrucciones para modificar
    el esquema de la base de datos, como por ejemplo eliminar todo el esquema y recrearlo,
    agregar una nueva tabla, añadir una nueva columna, etc
    */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    /**
     * vACIA LA TABLA DE CHUPITOS
     */
    public void clearTable(){
        SQLiteDatabase d = getWritableDatabase();
        d.execSQL("DELETE FROM "+ Tablas.TChupitos.TABLE_NAME + ";");

    }


    /**
     * GUARDA EL CHUPITO PASADO POR PARÁMETRO SI SU NOMBRE NO SE HA REPETIDO
     * @param chupito
     * @return
     */
    public long saveChupito(Chupito chupito) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                Tablas.TChupitos.TABLE_NAME,
                null,
                chupito.toContentValues());
    }


    /**
     * Devuelve todos los chupitos en un cursor
     * @return
     */
    public Cursor getAllChupitos() {
        return getReadableDatabase()
                .query(
                        Tablas.TChupitos.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getChupitoByName(String name) {
        Cursor c = getReadableDatabase().query(
                Tablas.TChupitos.TABLE_NAME,
                null,
                Tablas.TChupitos.NOMBRE + " LIKE ?",
                new String[]{name},
                null,
                null,
                null);
        return c;
    }

    public void removeChupitoByName(String name){
        SQLiteDatabase db = getWritableDatabase();
            db.execSQL("delete from "+ Tablas.TChupitos.TABLE_NAME+" where "+ Tablas.TChupitos.NOMBRE + "='"+name+"'");

    }

}
