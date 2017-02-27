package com.example.trjano.chupitos.BD;

import android.provider.BaseColumns;

/**
 * Created by trjano on 14/02/17.
 */

public class T {

    //esto contiene el nombre de las columnas de la tabla chupitos
    public static abstract class Chupitos_Table implements BaseColumns {
        public static final String TABLE_NAME ="chupitos";
        public static final String NOMBRE = "nombre";
        public static final String TIPO = "tipo";
        public static final String ING1 = "ingrediente1";
        public static final String ING2 = "ingrediente2";
        public static final String ING3 = "ingrediente3";
        public static final String ING4 = "ingrediente4";
        public static final String ING5 = "ingrediente5";
        public static final String DESC = "descripcion";
        public static final String FAV = "favoritos";
    }
}
