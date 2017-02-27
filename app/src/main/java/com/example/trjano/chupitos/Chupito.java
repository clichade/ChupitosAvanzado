package com.example.trjano.chupitos;

import android.content.ContentValues;

import com.example.trjano.chupitos.BD.T;

import java.util.ArrayList;

/**
 * Created by trjano on 26/01/2017.
 */

/**
 * voy a meterle el Builder pattern para el problema de los ingredientes
 */
public class Chupito {

    //El nombre del chupito
    String nombre;

    //La lista con el nombre de los ingredientes
    ArrayList<String> ingredientes;

    //Una descripción que quieras añadir al chupito
    String descripcion;

    //El tipo de chupito que puede ser SUAVE,EXOTICO O DURO
    Tipo tipo;


    public Chupito(String nombre,Tipo tipo,
                   String descripcion, ArrayList<String> ingredientes){
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }



    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIngredientes() {
        String string ="";
        for (String ingre:ingredientes) {
            string += "-" + ingre + "\n";
        }
        return string;
    }

    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Pasamos el chupito al tipo content values para prepararlo para la base de datos
     * @return
     */
    public ContentValues toContentValues(){
        ContentValues val = new ContentValues();
        val.put(T.Chupitos_Table.NOMBRE,nombre);
        val.put(T.Chupitos_Table.TIPO,tipo.toString());
        val.put(T.Chupitos_Table.ING1,ingredientes.get(0));

        if (ingredientes.size() >=2)
            val.put(T.Chupitos_Table.ING2,ingredientes.get(1));

        if (ingredientes.size() >=3)
            val.put(T.Chupitos_Table.ING3,ingredientes.get(2));

        if (ingredientes.size() >=4)
            val.put(T.Chupitos_Table.ING4,ingredientes.get(3));

        if (ingredientes.size() ==5)
            val.put(T.Chupitos_Table.ING5,ingredientes.get(4));

        val.put(T.Chupitos_Table.DESC,descripcion);
        return val;


    }

    /**
     *
     * @return dependiendo del tipo de chupito que es devolverá el identificador de una imagen u otra
     */
    public int getIcon(){
        int icon = R.mipmap.chupito_basico;
        switch (tipo){
            case SUAVE:
                icon = R.mipmap.draw_chupito_blue;
                break;
            case EXOTICO:
                icon = R.mipmap.draw_chupito_naranja;
                break;
            case DURO:
                icon = R.mipmap.draw_chupito_rojo;
                break;
        }

        return icon;
    }

}
