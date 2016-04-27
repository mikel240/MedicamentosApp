package com.example.atmosfera.medicamentosapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.atmosfera.medicamentosapp.R;
import com.example.atmosfera.medicamentosapp.pojo.Medicamento;

import java.util.ArrayList;

public class EditListAdapter extends ArrayAdapter<Medicamento> {

    private Context context;
    private ArrayList<Medicamento> items;
    private LayoutInflater layoutInflater;

    public EditListAdapter(Context context, ArrayList<Medicamento> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        Medicamento m = items.get(position);
        v = layoutInflater.inflate(R.layout.medication, null);

        TextView tvId = (TextView) v.findViewById(R.id.tvId);
        TextView tvNombre = (TextView) v.findViewById(R.id.tvNombre);
        TextView tvIntervalo = (TextView) v.findViewById(R.id.tvIntervalo);
        TextView tvDuracion = (TextView) v.findViewById(R.id.tvDuracion);

        tvId.setText("" + m.getIdMedicamento());

        tvNombre.setText(m.getIdMedicamento() + " " + m.getNombre());

        int iIntervalo = m.getIntervalo();
        String sIntervalo = (iIntervalo < 24 ? iIntervalo + "h" : (iIntervalo / 24) + "d");
        tvIntervalo.setText("Cada " + sIntervalo);

        int iDuracion = m.getDuracion();
        String sDuracion;
        if (iDuracion < 7) {
            sDuracion = iDuracion + "d";
        } else if (iDuracion < 30) {
            sDuracion = (iDuracion / 7) + "s";
        } else {
            sDuracion = (iDuracion / 30) + "m";
        }

        tvDuracion.setText("Durante  " + sDuracion);


        return v;
    }

}