package com.example.trjano.chupitos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by trjano on 27/01/2017.
 */

public class Custom_Adapter extends ArrayAdapter<Chupito> {
    private ArrayList<Chupito> listC;//la lista de chupitos que mostrará
    Context context;//el contexto de la activity de listview
    int resource;//la id del layout de fila row


    /**
     *
     * @param context el contexto de la activity principal
     * @param resource la id del layout de las filas
     * @param listC la lista de chupitos qeu será usada para desplegar los datos
     */
    public Custom_Adapter(Context context, int resource, ArrayList<Chupito> listC) {
        super(context, resource, listC);
        this.resource = resource;
        this.listC = listC;
        this.context = context;
    }

    /**
     *  This method will be called for every item in the ListView to create views
     *  with their properties set as we want.
     *  Is also using a temporary holder class declared inside the WeatherAdapter class.
     *  This class will be used to cache the TextView so they can be reused for every row in the
     *  ListView and this will provide us a great
     *  performance improvement as we are recycling the same two views with different properties and we
     *  don’t need to find ImageView and TextView for every ListView item. The above code is also using
     *  the Android built in Layout Inflator to inflate (parse) the xml layout file.
     * @param position of the actual chupito
     * @param convertView the view of the row
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RowData rowData;


            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
            rowData = new RowData();
            rowData.tvTitle = (TextView) row.findViewById(R.id.tvTitle);
            rowData.ivIcon = (ImageView) row.findViewById(R.id.ivIcon);
            row.setTag(rowData);


        Chupito chup = listC.get(position);
        rowData.tvTitle.setText(chup.nombre);
        rowData.ivIcon.setImageResource(chup.getIcon());
        return row;
    }


    static class RowData
    {
        ImageView ivIcon;
        TextView tvTitle;
    }
}
