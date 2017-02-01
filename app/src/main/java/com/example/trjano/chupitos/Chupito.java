package com.example.trjano.chupitos;

import java.util.ArrayList;

/**
 * Created by trjano on 26/01/2017.
 */

public class Chupito {



    String nombre;

     ArrayList<String> ingredientes;

    String descripcion;

    Tipo tipo;



    public Chupito(){
        nombre = "basico";
        ingredientes = new ArrayList<String>();
        ingredientes.add("Ingrediente basico \n");
        tipo = Tipo.SUAVE;
        descripcion = "Descripción básica";
    }

    public Chupito(String nombre,Tipo tipo, String descripcion, String ingrediente1){
        this.nombre = nombre;
        ingredientes = new ArrayList<String>();
        this.tipo = tipo;
        ingredientes.add(ingrediente1 +" \n");
        this.descripcion = descripcion;
    }
    public Chupito(String nombre,Tipo tipo, String descripcion, String ingre1, String ingre2){
        this.nombre = nombre;
        ingredientes = new ArrayList<String>();
        this.tipo = tipo;
        ingredientes.add(ingre1 +" \n");
        ingredientes.add(ingre2 +" \n");
        this.descripcion = descripcion;
    }

    public Chupito(String nombre,Tipo tipo, String descripcion, String ingre1, String ingre2, String ingre3) {
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
