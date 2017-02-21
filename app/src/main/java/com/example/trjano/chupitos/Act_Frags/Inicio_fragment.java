package com.example.trjano.chupitos.Act_Frags;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.trjano.chupitos.BD.ChupitosDB;
import com.example.trjano.chupitos.BD.Tablas;
import com.example.trjano.chupitos.Chupito;
import com.example.trjano.chupitos.R;
import com.example.trjano.chupitos.Tipo;

import java.util.ArrayList;

/**
 * Un fragment es digamos una sub activity (una activity dentro de otra), y se usa dentro de una acivity
 */

public class Inicio_fragment extends Fragment implements View.OnClickListener{

    Button btSuave; Button btExotico; Button btA_Matar;
    TextView tvTipos;
    ArrayList<Chupito> listC;

    /**
     * onCreateView forma parte del ciclo de vida de un fragment y es donde se debe declarar su layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inicio_view,container,false);
    }

    /**
     * posterior a oncreateView() es donde se deben inicializar las estructuras
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listC = new ArrayList<>();
        cargarLista(listC);

        btSuave = (Button) getActivity().findViewById(R.id.btSuave);
        btExotico = (Button) getActivity().findViewById(R.id.btExotico);
        btA_Matar = (Button) getActivity().findViewById(R.id.btA_Matar) ;
        tvTipos = (TextView) getActivity().findViewById(R.id.tvTipos);


       //set on click listener les permite a los botones reaccionar a los clicks
        btSuave.setOnClickListener(this);
        btExotico.setOnClickListener(this);
        btA_Matar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(),Info_Activity.class);
        Bundle b = new Bundle();
        Chupito chupito = listC.get(0);


       switch (v.getId()){

            case R.id.btSuave:

                b.putString("ingredientes", chupito.getIngredientes());
                b.putString("nombre", chupito.getNombre());
                b.putString("descripcion",chupito.getDescripcion());
                b.putInt("ivIcon",chupito.getIcon());
                System.out.println("entra en suave");
                intent.putExtras(b);
                startActivity(intent);
                break;

            case R.id.btExotico:
                b.putString("ingredientes", chupito.getIngredientes());
                b.putString("nombre", chupito.getNombre());
                b.putString("descripcion",chupito.getDescripcion());
                b.putInt("ivIcon",chupito.getIcon());
                intent.putExtras(b);
                startActivity(intent);
                break;

            case R.id.btA_Matar:
                b.putString("ingredientes", chupito.getIngredientes());
                b.putString("nombre", chupito.getNombre());
                b.putString("descripcion",chupito.getDescripcion());
                b.putInt("ivIcon",chupito.getIcon());
                System.out.println("entra en matar");
                intent.putExtras(b);
                startActivity(intent);
                break;

        }


    }

    public void cargarLista(ArrayList<Chupito> listChupitos){

        ChupitosDB db = new ChupitosDB(getContext());
        Cursor c =db.getAllChupitos();

        while (c.moveToNext()){

            String name = c.getString(c.getColumnIndex(Tablas.TChupitos.NOMBRE));
            String tipo = c.getString(c.getColumnIndex(Tablas.TChupitos.TIPO));
            String desc = c.getString(c.getColumnIndex(Tablas.TChupitos.DESC));
            String ing1 = c.getString(c.getColumnIndex(Tablas.TChupitos.ING1));
            String ing2 = c.getString(c.getColumnIndex(Tablas.TChupitos.ING2));
            if (ing2 == null)
                listChupitos.add(new Chupito(name, Tipo.valueOf(tipo),desc,ing1));
            else {
                String ing3 = c.getString(c.getColumnIndex(Tablas.TChupitos.ING3));
                if (ing3 == null)
                    listChupitos.add(new Chupito(name,Tipo.valueOf(tipo),desc,ing1,ing2));
                else
                    listChupitos.add(new Chupito(name,Tipo.valueOf(tipo),desc,ing1,ing2,ing3));
            }
        }
    }
}
