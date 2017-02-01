package com.example.trjano.chupitos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trjano on 31/01/2017.
 */

public class Cargador  {

    private static BufferedReader bufferRead;
    private  ArrayList<Chupito> listC;
    private static Cargador instance;
    Context context;

    static int MAXCAMPOS = 6;


    private  Cargador (Context context)  {
        try {
        listC = new ArrayList<>();
        this.context = context;
            procesarFichero();
            System.out.println("El tamaño es " + listC.size());
        } catch (IOException e) {
            System.out.println("no se ha encontrado el archivo");
            e.printStackTrace();
        }
    }


    public static Cargador getInstance(Context context) {
        if(instance == null){
            instance = new Cargador(context);
        }
        return instance;
    }

    public ArrayList<Chupito> getListC() {
        return listC;
    }

    public  void procesarFichero() throws IOException {
        //**String vCampos[]=new String[MAXCAMPOS];

        List<String> vCampos = new ArrayList<String>();
        String S = new String();
        int numCampos = 0;
        System.out.println("Procensando el fichero...");



        //necesita un contexto para funcionar y lee el archivo desde el raw
        bufferRead = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.load_file)));

        while (( S = bufferRead.readLine()) != null) {
            System.out.println("S: " + S);
            if(!S.startsWith("--")) {
                vCampos.clear();
                trocearLinea(S, vCampos);
                crear(vCampos.size(), vCampos);
            }
        }
        System.out.println("Procesamiento finalizado con éxito");//cerramos el filtro
        bufferRead.close();

    }


        /**
         * Metodo para trocear cada l�nea y separarla por campos
         * @param S cadena con la l�nea completa le�da
         * @param vCampos Array de String que contiene en cada posici�n un campo distinto
         * @return numCampos. N�mero campos encontrados
         */
        private  void trocearLinea(String S, List<String> vCampos) {
            boolean eol = false;
            int pos = 0, posini = 0;

                while (!eol) {
                    pos = S.indexOf("#");
                    if (pos != -1) {
                        vCampos.add(new String(S.substring(posini, pos)));
                        S = S.substring(pos + 1, S.length());
                    } else eol = true;
                }

        }

    /**hay qu eponer un exception
     *
     * @param numcampos
     * @param vCampos
     */
        private void crear(int numcampos, List<String> vCampos){



            if(vCampos.size() >2) {
                System.out.println("I del if tambien");
                String tipo = vCampos.get(0);

                switch (tipo) {
                    case "SUAVE":
                        listC.add(instanciaChupito(numcampos, vCampos, Tipo.SUAVE));
                        break;

                    case "EXOTICO":
                        listC.add(instanciaChupito(numcampos, vCampos, Tipo.EXOTICO));
                        break;

                    case "DURO":
                        listC.add(instanciaChupito(numcampos, vCampos, Tipo.DURO));
                        break;

                    default:
                        System.out.println("No coincide con ninguno de los tipos");
                }
            }
        }

    private Chupito instanciaChupito(int numcampos, List<String> vCampos,Tipo tipo){
        Chupito ch;
        String nombre = vCampos.get(1);
        String ing1;
        String ing2;
        String ing3;
        String descripcion;



        switch (numcampos){
            case 4:
                ing1 = vCampos.get(3);
                descripcion = vCampos.get(2);
                ch = new Chupito(nombre,tipo,descripcion,ing1);
                break;

            case 5:
                ing1 = vCampos.get(3);
                ing2 = vCampos.get(4);
                descripcion = vCampos.get(2);
                ch = new Chupito(nombre,tipo,descripcion,ing1,ing2);
                break;

            case 6:
                ing1 = vCampos.get(3);
                ing2 = vCampos.get(4);
                ing3 = vCampos.get(5);
                descripcion = vCampos.get(2);
                ch = new Chupito(nombre,tipo,descripcion,ing1,ing2,ing3);
                break;

            default:
                ch = new Chupito("ERRORNCAMPOS",Tipo.SUAVE,"","");

        }


        return ch;
    }


}
