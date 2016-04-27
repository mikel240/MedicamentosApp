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

public class RegistroFragment extends Fragment {
    private View view;
    private MainActivity activity;

    private ArrayList<Aviso> listaAvisosRegistro;
    private ListView lvRegistro;

    private CustomArrayAdapter caRegistro;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.registro_fragment, null);
        activity = (MainActivity) getActivity();

        listaAvisosRegistro = activity.getListaAvisosRegistro();
        lvRegistro = (ListView) view.findViewById(R.id.lvRegistro);

        drawRegistro();

        return view;
    }


    private void drawRegistro() {

        String ultFecha = "", fechaAux = "";

        ArrayList<Object> lista = new ArrayList<>();

        for (Aviso aviso : listaAvisosRegistro) {
            ultFecha = aviso.getFechaAviso();

            if (!ultFecha.equals(fechaAux)) {
                fechaAux = aviso.getFechaAviso();
                lista.add(fechaAux);
            }

            lista.add(aviso);
        }

        listaAvisosRegistro.clear();

        caRegistro = new CustomArrayAdapter(getContext(), lista);

        lvRegistro.setAdapter(caRegistro);

    }
}
