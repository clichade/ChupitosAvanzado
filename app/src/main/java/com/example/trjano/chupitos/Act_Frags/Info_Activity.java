package com.example.trjano.chupitos.Act_Frags;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trjano.chupitos.R;

/**
 * Created by trjano on 24/01/2017.
 */

public class Info_Activity extends AppCompatActivity  {
    ImageView ivPhoto;
    TextView tvIngredients;
    TextView tvDescription;
    TextView tvNombreChupito;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        ivPhoto = (ImageView) findViewById(R.id.ivphoto);
        tvIngredients = (TextView) findViewById(R.id.tvIngredients);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvNombreChupito = (TextView) findViewById(R.id.tvNombreChupito);

        Bundle b = getIntent().getExtras();
        if(b != null){
            String aux = b.getString("ingredientes");
            tvIngredients.setText(aux);
            aux = b.getString("descripcion");
            tvDescription.setText(aux);
            aux = b.getString("nombre");
            tvNombreChupito.setText(aux);
            ivPhoto.setImageResource(b.getInt("ivIcon"));
        }

    }
}
