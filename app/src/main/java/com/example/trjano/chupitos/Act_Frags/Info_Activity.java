package com.example.trjano.chupitos.Act_Frags;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trjano.chupitos.BD.ChupitosDB;
import com.example.trjano.chupitos.R;

/**
 * Created by trjano on 24/01/2017.
 */

public class Info_Activity extends AppCompatActivity  {
    ImageView ivPhoto;
    TextView tvIngredients;
    TextView tvDescription;
    TextView tvNombreChupito;
    TextView tvTipo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        //cargamos las variables con las de la layout info
        ivPhoto = (ImageView) findViewById(R.id.ivphoto);
        tvIngredients = (TextView) findViewById(R.id.tvIngredient);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvNombreChupito = (TextView) findViewById(R.id.tvNombreChupito);
        tvTipo = (TextView) findViewById(R.id.tvTipoInfo);

        //recibimos los extras que serán ingredientes , descripción, nombre e ivIcon
        Bundle b = getIntent().getExtras();
        if(b != null){
            String aux = b.getString("ingredientes");
            tvIngredients.setText(aux);
            aux = b.getString("descripcion");
            tvDescription.setText(aux);
            aux = b.getString("nombre");
            tvNombreChupito.setText(aux);
            ivPhoto.setImageResource(b.getInt("ivIcon"));
            aux = b.getString("tipo");
            tvTipo.setText(aux);
        }

    }

    /**
     * cargamos  el menú con un inflater usando el layout menu_info
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_info, menu);
        return super.onCreateOptionsMenu(menu);


    }

    /**
     * trabaja con el menú para hacer sus acciones
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.m_borrar:
                call_Remove_dialog();
                return true;
            case R.id.m_favoritos:

                Toast.makeText(getApplicationContext(),"Favoritos pulsado",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void add_to_favorites(){
        ChupitosDB bd = new ChupitosDB(getBaseContext());
        bd.addToFavorites(tvNombreChupito.getText().toString());
    }
    /**
     * creamos un diálogo de alerta por defecto y hacemos que al pulsar si  borramos el chupito actual
     * si no simplemente cancelarmos el remove dialog
     */
    public void call_Remove_dialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("AVISO");

        // set dialog message
        alertDialogBuilder
                .setMessage("¿Desea borrar el chupito?")
                .setCancelable(false)
                .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //instanciamos la base de datos
                        ChupitosDB bd = new ChupitosDB(getBaseContext());
                        //eliminamos los chupitos con ese nombre
                        bd.removeChupitoByName(tvNombreChupito.getText().toString());
                        Toast.makeText(getApplicationContext(),"Chupito borrado",Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}
