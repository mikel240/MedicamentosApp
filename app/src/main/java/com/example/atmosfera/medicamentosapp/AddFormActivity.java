package com.example.atmosfera.medicamentosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.atmosfera.medicamentosapp.adapters.ViewPagerAdapter;
import com.example.atmosfera.medicamentosapp.fragments.Image1SliderFragment;
import com.example.atmosfera.medicamentosapp.fragments.Image2SliderFragment;
import com.example.atmosfera.medicamentosapp.fragments.Image3SliderFragment;

public class AddFormActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form);

        //Agregar custom ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.toolbar_title_add));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //Precargar Spinner
        setupSpinnerVia();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Back arrow
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Image1SliderFragment(), "pastillas");
        adapter.addFragment(new Image2SliderFragment(), "inyeccion");
        adapter.addFragment(new Image3SliderFragment(), "jarabe");

        viewPager.setAdapter(adapter);
    }

    private void setupSpinnerVia() {
        Spinner spinner = (Spinner) findViewById(R.id.via);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.vias_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void openFrecuenciaActivity(View v) {
        String nombre = ((EditText) findViewById(R.id.name)).getText().toString();
        int forma = ((ViewPager) findViewById(R.id.viewpager)).getCurrentItem();
        String via = ((Spinner) findViewById(R.id.via)).getSelectedItem().toString();

        if (nombre.length() != 0) {
            Intent in = new Intent(this, FrecuenciaActivity.class).
                    putExtra("nombre", nombre).
                    putExtra("forma", forma).
                    putExtra("via", via);
            startActivity(in);
        } else {
            Toast.makeText(AddFormActivity.this, getResources().getString(R.string.campo_name_vacio), Toast.LENGTH_LONG).show();
        }
    }

}
