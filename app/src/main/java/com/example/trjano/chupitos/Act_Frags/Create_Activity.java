package com.example.trjano.chupitos.Act_Frags;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trjano.chupitos.BD.ChupitosDB;
import com.example.trjano.chupitos.Chupito;
import com.example.trjano.chupitos.R;
import com.example.trjano.chupitos.Tipo;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Acticity en la que se añadirán nuevos chupitos a la lista.
 */
public class Create_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Tipo tipoSelec;
    EditText etNombre, etIng1, etIng2, etIng3 , etDesc;
    Button btAnadir;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //inicializamos los edit text
        etNombre = (EditText) findViewById(R.id.etNombre);
        etIng1 = (EditText) findViewById(R.id.etIng1);
        etIng2 = (EditText) findViewById(R.id.etIng2);
        etIng3 = (EditText) findViewById(R.id.etIng3);
        etDesc = (EditText) findViewById(R.id.etDesc);

        btAnadir = (Button) findViewById(R.id.btAdd);

        spinner = (Spinner) findViewById(R.id.spinner);//inicializamos el spinner
        addItemsOnSpinner();
        spinner.setOnItemSelectedListener(this);

        btAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAddConditions()) {
                    anadirChupito();
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * definimos una Array list para las casillas del listener y creamos un array adapter<String>
     * de parámetros
     * El contexto this
     * La id de la layout para el spinner (ponemos la básica de android  R.layout.support_simple_spinner_dropdown_item)
     * y la lista que usaremos para adaptar spinnerlist
     */
    private void addItemsOnSpinner() {
        ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add("SUAVE");
        spinnerList.add("EXOTICO");
        spinnerList.add("DURO");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, spinnerList);
        spinner.setAdapter(adapter);
    }

    /**
     * reaccionan con el spinner
     * si se ha escogido algun item de spinner se graba en tiposeleccionado
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String s = parent.getItemAtPosition(position).toString();
        tipoSelec = Tipo.valueOf(s);
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    //reaccina con el spinner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean checkAddConditions() {
        boolean valid = true;
        String s = String.valueOf(etNombre.getText());
        if (s.matches("")) {
            valid = false;
            Toast.makeText(this, "No se ha escrito ningún nombre", Toast.LENGTH_SHORT).show();
        } else if (String.valueOf(etIng1.getText()) == "") {
            valid = false;
            Toast.makeText(this, "No hay ningún primer ingrediente", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    /**
     * añade un chupito cogiendo como parámetros el contenido de los edit text , si están vacios no se pone nada
     */
    public void anadirChupito(){
        String nombre = etNombre.getText().toString();
        String ing1= etIng1.getText().toString();
        String ing2= etIng2.getText().toString();
        String ing3= etIng3.getText().toString();
        String desc = etDesc.getText().toString();
        Tipo tipo = tipoSelec;
        Chupito c;

        int n_ing = 1;
        if(ing2 != "") {
            n_ing++;
            if (ing3 != "")
                n_ing++;
        }

       switch (n_ing){
           case 1:
               c = new Chupito(nombre,tipo,desc,ing1);
               break;
           case 2:
               c = new Chupito(nombre,tipo,desc,ing1,ing2);
               break;
           case 3:
           default:
               c = new Chupito(nombre,tipo,desc,ing1,ing2,ing3);
               break;
       }

        ChupitosDB db = new ChupitosDB(getApplicationContext());
        db.saveChupito(c);
        Toast.makeText(this, "Chupito creado con éxito", Toast.LENGTH_SHORT).show();
        finish();

    }

}

