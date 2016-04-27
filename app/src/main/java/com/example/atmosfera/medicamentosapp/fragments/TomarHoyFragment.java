package com.example.atmosfera.medicamentosapp.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.atmosfera.medicamentosapp.MainActivity;
import com.example.atmosfera.medicamentosapp.R;
import com.example.atmosfera.medicamentosapp.adapters.CustomArrayAdapter;
import com.example.atmosfera.medicamentosapp.pojo.Aviso;

import java.util.ArrayList;

public class TomarHoyFragment extends Fragment {
    private View view;
    private MainActivity activity;

    private ArrayList<Aviso> listaAvisosTomarHoy;
    private ListView lvTomarHoy;

    private CustomArrayAdapter caTomarHoy;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tomar_hoy_fragment, null);
        activity = (MainActivity) getActivity();

        listaAvisosTomarHoy = activity.getListaAvisosTomarHoy();
        lvTomarHoy = (ListView) view.findViewById(R.id.lvTomarHoy);

        drawTomarHoy();

        return view;
    }


    private void drawTomarHoy() {

        String ultFecha = "", fechaAux = "";

        ArrayList<Object> lista = new ArrayList<>();

        for (Aviso aviso : listaAvisosTomarHoy) {
            ultFecha = aviso.getFechaAviso();

            if (!ultFecha.equals(fechaAux)) {
                fechaAux = aviso.getFechaAviso();
                lista.add(fechaAux);
            }

            lista.add(aviso);
        }

        listaAvisosTomarHoy.clear();

        caTomarHoy = new CustomArrayAdapter(getContext(), lista);

        lvTomarHoy.setAdapter(caTomarHoy);

    }
}
