package com.example.atmosfera.medicamentosapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atmosfera.medicamentosapp.R;
import com.example.atmosfera.medicamentosapp.pojo.Aviso;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Object> {

    private final int IMGS[] = {R.drawable.ic_im_pastillas, R.drawable.im_inyeccion, R.drawable.im_jarabe};
    private Context context;
    private ArrayList<Object> items;
    private LayoutInflater layoutInflater;

    public CustomArrayAdapter(Context context, ArrayList<Object> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        Object o = items.get(position);
        if (o != null) {
            if (o instanceof String) {
                String fecha = (String) o;
                v = layoutInflater.inflate(R.layout.display_medication_head, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                TextView sectionView = (TextView) v.findViewById(R.id.textHora);
                String fechaAux = fecha.substring(8, 10) + "/" + fecha.substring(5, 7) + "/" + fecha.substring(0, 4);
                sectionView.setText(fechaAux);

            } else {
                Aviso row = (Aviso) o;
                v = layoutInflater.inflate(R.layout.display_medication_row, null);

                ImageView imForma = (ImageView) v.findViewById(R.id.imageView);
                TextView nombre = (TextView) v.findViewById(R.id.textName);
                TextView frecuencia = (TextView) v.findViewById(R.id.textFrecuencia);
                TextView hora = (TextView) v.findViewById(R.id.textHora);

                imForma.setImageResource(IMGS[row.getMedicamento().getForma()]);
                nombre.setText(row.getMedicamento().getNombre());

                frecuencia.setText("Vía: " + row.getMedicamento().getVia());
                hora.setText(row.getHoraAviso());
            }
        }
        return v;
    }

}