package com.example.atmosfera.medicamentosapp.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atmosfera.medicamentosapp.R;
import com.example.atmosfera.medicamentosapp.databases.SqlHelper;
import com.example.atmosfera.medicamentosapp.pojo.Aviso;

import java.util.ArrayList;

public class TomarHoyFragment extends Fragment {
    private ArrayList<Aviso> listaAvisos = null;

    public TomarHoyFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tomar_hoy_fragment, null);
        TextView tvTest = (TextView) view.findViewById(R.id.tvTest);
        /*String res = "";
        listaAvisos = SqlHelper.getInstance(getContext()).getAvisosHoy();
        for (Aviso a : listaAvisos) {
            res += a.getIdMedicamento() + " " + a.getMedicamento().getNombre() + " " + a.getFechaAviso() + " " + a.getHoraAviso() + "\n";
        }*/

        tvTest.setText("holaa");
        return view;
    }

}
