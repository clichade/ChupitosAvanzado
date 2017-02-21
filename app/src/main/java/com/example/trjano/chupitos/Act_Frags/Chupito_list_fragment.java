package com.example.trjano.chupitos.Act_Frags;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.trjano.chupitos.BD.ChupitosDB;
import com.example.trjano.chupitos.BD.Tablas;
import com.example.trjano.chupitos.Chupito;
import com.example.trjano.chupitos.Custom_Adapter;
import com.example.trjano.chupitos.R;
import com.example.trjano.chupitos.Tipo;

import java.util.ArrayList;

public class Chupito_list_fragment extends Fragment implements AdapterView.OnItemClickListener {

    ArrayList<Chupito> listChupitos;
    ListView listView;
    Button btAnadir;
    Custom_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_list, container, false);

    }
//Como esto no es una activity sino un fragment lo que hacemos es poner getActivity()
    //en lugar de this
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //inicializo la lista y los ocomponentes
        listChupitos = new ArrayList<>();
        cargarLista(listChupitos);
        listView = (ListView) getActivity().findViewById(R.id.lvList_chupito);
        btAnadir = (Button) getActivity().findViewById(R.id.btAnadir);


        adapter = new Custom_Adapter(getActivity(), R.layout.row_listview, listChupitos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        btAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Create_Activity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //lo limpio, vuelvo a insertar
        adapter.clear();
        cargarLista(listChupitos);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Chupito chupito = listChupitos.get(position);

        Intent intent = new Intent(getActivity(), Info_Activity.class);
        Bundle b = new Bundle();
        b.putString("ingredientes", chupito.getIngredientes());
        b.putString("nombre", chupito.getNombre());
        b.putString("descripcion", chupito.getDescripcion());
        b.putInt("ivIcon", chupito.getIcon());


        intent.putExtras(b);
        startActivity(intent);
    }


    public void cargarLista(ArrayList<Chupito> listChupitos) {

        ChupitosDB db = new ChupitosDB(getContext());
        Cursor c = db.getAllChupitos();

        while (c.moveToNext()) {

            String name = c.getString(c.getColumnIndex(Tablas.TChupitos.NOMBRE));
            String tipo = c.getString(c.getColumnIndex(Tablas.TChupitos.TIPO));
            String desc = c.getString(c.getColumnIndex(Tablas.TChupitos.DESC));
            String ing1 = c.getString(c.getColumnIndex(Tablas.TChupitos.ING1));
            String ing2 = c.getString(c.getColumnIndex(Tablas.TChupitos.ING2));
            if (ing2 == null)
                listChupitos.add(new Chupito(name, Tipo.valueOf(tipo), desc, ing1));
            else {
                String ing3 = c.getString(c.getColumnIndex(Tablas.TChupitos.ING3));
                if (ing3 == null)
                    listChupitos.add(new Chupito(name, Tipo.valueOf(tipo), desc, ing1, ing2));
                else
                    listChupitos.add(new Chupito(name, Tipo.valueOf(tipo), desc, ing1, ing2, ing3));
            }
        }
    }
}

