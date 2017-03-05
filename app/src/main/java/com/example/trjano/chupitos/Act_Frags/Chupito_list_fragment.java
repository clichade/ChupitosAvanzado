package com.example.trjano.chupitos.Act_Frags;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.trjano.chupitos.BD.BDatos;
import com.example.trjano.chupitos.Chupito;
import com.example.trjano.chupitos.Custom_Adapter;
import com.example.trjano.chupitos.R;

import java.util.ArrayList;

public class Chupito_list_fragment extends Fragment implements AdapterView.OnItemClickListener {

    ArrayList<Chupito> listChupitos;
    ListView listView;
    Button btAnadir;
    Custom_Adapter adapter;
    BDatos bd;

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

        bd = new BDatos(getContext());
        //inicializo la lista y los ocomponentes
        listChupitos = new ArrayList<>();
        bd.cargarLista(listChupitos);
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
        bd.cargarLista(listChupitos);
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
        b.putString("tipo", chupito.getTipo().toString());


        intent.putExtras(b);
        startActivity(intent);
    }


}


