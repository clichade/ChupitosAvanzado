package com.example.trjano.chupitos;

import android.content.ContentValues;

import com.example.trjano.chupitos.BD.Tablas;

import java.util.ArrayList;

/**
 * Created by trjano on 26/01/2017.
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
                   String descripcion, String ingrediente1){
        this.nombre = nombre;
        ingredientes = new ArrayList<String>();
        this.tipo = tipo;
        ingredientes.add(ingrediente1 +" \n");
        this.descripcion = descripcion;
    }
    public Chupito(String nombre,Tipo tipo, String descripcion,
                   String ingre1, String ingre2){
        this.nombre = nombre;
        ingredientes = new ArrayList<String>();
        this.tipo = tipo;
        ingredientes.add(ingre1 +" \n");
        ingredientes.add(ingre2 +" \n");
        this.descripcion = descripcion;
    }

    public Chupito(String nombre,Tipo tipo, String descripcion,
                   String ingre1, String ingre2, String ingre3) {
        this.nombre = nombre;
        ingredientes = new ArrayList<String>();
        this.tipo = tipo;
        ingredientes.add(ingre1 + " \n");
        ingredientes.add(ingre2 + " \n");
        ingredientes.add(ingre3 + " \n");
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
        val.put(Tablas.TChupitos.NOMBRE,nombre);
        val.put(Tablas.TChupitos.TIPO,tipo.toString());
        val.put(Tablas.TChupitos.ING1,ingredientes.get(0));

        if (ingredientes.size() >=2)
            val.put(Tablas.TChupitos.ING2,ingredientes.get(1));

        if (ingredientes.size() >=3)
            val.put(Tablas.TChupitos.ING3,ingredientes.get(2));

        val.put(Tablas.TChupitos.DESC,descripcion);
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
