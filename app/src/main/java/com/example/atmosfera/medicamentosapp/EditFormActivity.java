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
import com.example.atmosfera.medicamentosapp.databases.SqlHelper;
import com.example.atmosfera.medicamentosapp.fragments.Image1SliderFragment;
import com.example.atmosfera.medicamentosapp.fragments.Image2SliderFragment;
import com.example.atmosfera.medicamentosapp.fragments.Image3SliderFragment;
import com.example.atmosfera.medicamentosapp.pojo.Medicamento;

public class EditFormActivity extends AppCompatActivity {

    Medicamento m;
    Toolbar toolbar;
    ViewPager viewPager;
    Spinner spinner;
    EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_form);

        //Agregar custom ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.toolbar_title_edit));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int id = getIntent().getIntExtra("id", -1);
        if (id == -1) {
            return;
        }

        m = SqlHelper.getInstance(this).getMedicamento(id);
        if (m == null) {
            return;
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setCurrentItem(m.getForma());

        //Precargar Spinner
        setupSpinnerVia();
        String arr[] = getResources().getStringArray(R.array.vias_array);

        int pos = 0;
        while (pos < arr.length) {
            if (m.getVia().equals(arr[pos])) {
                break;
            }
            pos++;
        }

        spinner.setSelection(pos);

        etNombre = (EditText) findViewById(R.id.name);
        etNombre.setText(m.getNombre());
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
        spinner = (Spinner) findViewById(R.id.via);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.vias_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void openEditFrecuenciaActivity(View v) {
        String nombre = etNombre.getText().toString();
        int forma = ((ViewPager) findViewById(R.id.viewpager)).getCurrentItem();
        String via = ((Spinner) findViewById(R.id.via)).getSelectedItem().toString();

        if (nombre.length() != 0) {
            Intent in = new Intent(this, EditFrecuenciaActivity.class).
                    putExtra("id", m.getIdMedicamento()).
                    putExtra("nombre", nombre).
                    putExtra("forma", forma).
                    putExtra("via", via).
                    putExtra("hora", m.getHoraPrimeraIngesta()).
                    putExtra("intervalo", "" + m.getIntervalo()).
                    putExtra("duracion", "" + m.getDuracion());
            startActivity(in);
        } else {
            Toast.makeText(EditFormActivity.this, getResources().getString(R.string.campo_name_vacio), Toast.LENGTH_LONG).show();
        }
    }

}
