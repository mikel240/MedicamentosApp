package com.example.atmosfera.medicamentosapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.atmosfera.medicamentosapp.AddFormActivity;
import com.example.atmosfera.medicamentosapp.EditListActivity;
import com.example.atmosfera.medicamentosapp.R;

public class DashboardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dashboard_fragment, container, false);
        ImageButton imageButtonAdd = (ImageButton) v.findViewById(R.id.imageButton);
        openAddForm(imageButtonAdd);

        ImageButton imageButtonEdit = (ImageButton) v.findViewById(R.id.imageButton2);
        openEditList(imageButtonEdit);

        return v;
    }

    private void openAddForm(ImageButton imButton) {
        imButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddFormActivity.class);
                startActivity(in);
            }
        });
    }

    private void openEditList(ImageButton imButton) {
        imButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), EditListActivity.class);
                startActivity(in);
            }
        });
    }

}
