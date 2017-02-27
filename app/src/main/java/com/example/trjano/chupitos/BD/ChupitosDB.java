package com.example.trjano.chupitos.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.trjano.chupitos.Chupito;
import com.example.trjano.chupitos.Tipo;

import java.util.ArrayList;


/**
 * SQliteOpenHelper es una clase que nos permite administrar una base de dtos de forma sencilla
 */

public class ChupitosDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "chupitos.db";

    /**
     * el constructor compara con su versión anterior
     * @param context
     */
    public ChupitosDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Es recomendable que la llave primaria sea BaseColumns._ID, ya que el framework de
     * Android usa esta referencia internamente en varios procesos.
      * @param db
     * Este método solo se ejecuta una vez, cuando se van a crear las tablas
     *
     *Este método solo se llama una vez la primera vez que se crea una base de datos
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + T.Chupitos_Table.TABLE_NAME + " ("
                + T.Chupitos_Table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + T.Chupitos_Table.NOMBRE + " TEXT NOT NULL,"
                + T.Chupitos_Table.TIPO + " TEXT NOT NULL,"
                + T.Chupitos_Table.ING1 + " TEXT NOT NULL,"
                + T.Chupitos_Table.ING2 + " TEXT,"
                + T.Chupitos_Table.ING3 + " TEXT,"
                + T.Chupitos_Table.ING4 + " TEXT,"
                + T.Chupitos_Table.ING5 + " TEXT,"
                + T.Chupitos_Table.DESC + " TEXT,"
                + T.Chupitos_Table.FAV + " NUM NOT NULL DEFAULT 0,"
                + "CHECK ("+T.Chupitos_Table.FAV+" IN (0,1)),"
                + "UNIQUE ("+ T.Chupitos_Table.NOMBRE+"));");

        ArrayList<String> ingList = new ArrayList<>();
        ingList.add("Jagger");
        ingList.add("Lima");
        Chupito c =new Chupito("Jagger", Tipo.SUAVE,"Es jagger, mola al principio",ingList);
        mockChupito(db,c);
        ingList.clear();
        ingList.add("Trópico");
        ingList.add("Azul");
        ingList.add("Magia");
        c = new Chupito("Blue Tropic",Tipo.EXOTICO,"Me gusta el nombre en verdad",ingList);
        mockChupito(db,c);
        ingList.clear();
        ingList.add("vamos");
        ingList.add("A probar");
        ingList.add("Con 5");
        ingList.add("Putisimos");
        ingList.add("Ingredientes");
        c = new Chupito("5 Reyes",Tipo.DURO,"La verdad es que hay uno mas que en el dark souls",ingList);
        mockChupito(db,c);


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
     * vacía todas las tuplas de la tabla de chupitos
     */
    public void clearTable(){
        SQLiteDatabase d = getWritableDatabase();
        d.execSQL("DELETE FROM "+ T.Chupitos_Table.TABLE_NAME + ";");

    }

    private long mockChupito(SQLiteDatabase db,Chupito chupito){
        return db.insert(
                T.Chupitos_Table.TABLE_NAME,
                null,
                chupito.toContentValues());
    }


    /**
     * guarda el chupito si no su nombre no se ha repetido
     * @param chupito
     * @return
     */
    public long saveChupito(Chupito chupito) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                T.Chupitos_Table.TABLE_NAME,
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
                        T.Chupitos_Table.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    /**
     * devuelve los chupitos aunque debería ser solo uno que tenan el mismo nombre que el introducido
     * por parámetro
     * @param name
     * @return
     */
    public Cursor getChupitoByName(String name) {
        //una query es una consulta sobre la base de datos legible y se pasa como un cursor
        Cursor c = getReadableDatabase().query(
                T.Chupitos_Table.TABLE_NAME,
                null,
                T.Chupitos_Table.NOMBRE + " LIKE ?",
                new String[]{name},
                null,
                null,
                null);
        return c;
    }

    /**
     * eliminamos el chupito que corresponde con el nombre pasado por parámetro
     * @param name
     */
    public void removeChupitoByName(String name){
        //ejecutamos un código sql que elimina los chupitos con el mismo nombre de la base de datos
        SQLiteDatabase db = getWritableDatabase();//obtenemos la base de datos
            db.execSQL("delete from "+ T.Chupitos_Table.TABLE_NAME+//ejecutamos el codigo sql
                    " where "+ T.Chupitos_Table.NOMBRE + "='"+name+"'");

    }

}
