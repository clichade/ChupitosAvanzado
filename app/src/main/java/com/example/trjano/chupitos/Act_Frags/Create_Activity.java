package com.example.trjano.chupitos.Act_Frags;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trjano.chupitos.BD.BDatos;
import com.example.trjano.chupitos.Chupito;
import com.example.trjano.chupitos.R;
import com.example.trjano.chupitos.Tipo;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Acticity en la que se añadirán nuevos chupitos a la lista.
 */
public class Create_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Tipo tipoSelec;
    ArrayList<EditText> etIngList;//lista de edit text de los ingredientes
    ArrayList<LinearLayout>llIngList;
    EditText etNombre, etDesc;
    Button btAnadir;

    int n_ings;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        n_ings = 1;

        initializeLists();
        //inicializamos los edit text
        etNombre = (EditText) findViewById(R.id.etNombre);



        etDesc = (EditText) findViewById(R.id.etDesc);
        btAnadir = (Button) findViewById(R.id.btAdd);

        spinner = (Spinner) findViewById(R.id.spinner);//inicializamos el spinner
        addItemsOnSpinner();
        spinner.setOnItemSelectedListener(this);

        btAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAddConditions()) {
                    anadirChupito();
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initializeLists(){
        etIngList = new ArrayList<>();
        llIngList = new ArrayList<>();

        etIngList.add((EditText) findViewById(R.id.etIng1));
        etIngList.add((EditText) findViewById(R.id.etIng2));
        etIngList.add((EditText) findViewById(R.id.etIng3));
        etIngList.add((EditText) findViewById(R.id.etIng4));
        etIngList.add((EditText) findViewById(R.id.etIng5));

        llIngList.add((LinearLayout) findViewById(R.id.ll_ing1));
        llIngList.add((LinearLayout) findViewById(R.id.ll_ing2));
        llIngList.add((LinearLayout) findViewById(R.id.ll_ing3));
        llIngList.add((LinearLayout) findViewById(R.id.ll_ing4));
        llIngList.add((LinearLayout) findViewById(R.id.ll_ing5));


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
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
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
        } else if (String.valueOf(etIngList.get(0).getText()) == "") {
            valid = false;
            Toast.makeText(this, "No hay ningún primer ingrediente", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    /**
     * añade un chupito cogiendo como parámetros el contenido de los edit text , si están vacios no se pone nada
     */
    public void anadirChupito() {
        String nombre = etNombre.getText().toString();

        String desc = etDesc.getText().toString();
        Tipo tipo = tipoSelec;

        ArrayList<String> ingredientes = new ArrayList<>();
        boolean parar = false;
        for (int i = 0; i < etIngList.size(); i++) {
            ingredientes.add(etIngList.get(i).getText().toString());
        }

        Chupito c = new Chupito(nombre, tipo, desc, ingredientes);
        BDatos db = new BDatos(getApplicationContext());
        db.saveChupito(c);
        Toast.makeText(this, "Chupito "+nombre+" creado con éxito", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void incIngr() {
        if (n_ings < 5) {
            llIngList.get(n_ings).setVisibility(View.VISIBLE);
            n_ings++;
        }
    }

    public void decIngr(){
        if (n_ings >= 1){
            llIngList.get(n_ings -1).setVisibility(View.INVISIBLE);
            etIngList.get(n_ings -1).setText("");
            n_ings--;
    }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.m_anadir:
                incIngr();
                return true;

            case R.id.m_quitar:
                decIngr();
                return true;

            case R.id.m_limpiarInfo:
                for (EditText t : etIngList)
                    t.setText("");
                return true;

            default: return super.onOptionsItemSelected(item);
        }

    }
}



