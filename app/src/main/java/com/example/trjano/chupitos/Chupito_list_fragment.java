package com.example.trjano.chupitos;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Chupito_list_fragment extends Fragment implements AdapterView.OnItemClickListener {

    ArrayList<Chupito> listChupitos;
    ListView listView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_list,container,false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //como esto no es una activity sino un fragment ponemos getActivity() en vez de this
        listChupitos = Cargador.getInstance(getActivity()).getListC();//cargamos la lista del loader a la lista de chupitos
        Custom_Adapter adapter = new Custom_Adapter(getActivity(),R.layout.row_listview,listChupitos);
        listView = (ListView) getActivity().findViewById(R.id.lvList_chupito);

        //el header sería para añadir un primero a
        //a la lista que indicase en que consiste
       // View header = (View) getLayoutInflater().inflate(R.layout.row_listview,null);
       // listView.addHeaderView(header);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Chupito chupito = listChupitos.get(position);

        Intent intent = new Intent(getActivity(),Chupito_Activity.class);
        Bundle b = new Bundle();
        b.putString("ingredientes", chupito.getIngredientes());
        b.putString("nombre", chupito.getNombre());
        b.putString("descripcion",chupito.getDescripcion());
        b.putInt("ivIcon",chupito.getIcon());


        intent.putExtras(b);
        startActivity(intent);
    }
}
