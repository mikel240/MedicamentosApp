package com.example.atmosfera.medicamentosapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.atmosfera.medicamentosapp.adapters.EditListAdapter;
import com.example.atmosfera.medicamentosapp.databases.SqlHelper;
import com.example.atmosfera.medicamentosapp.pojo.Medicamento;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class EditListActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvEditList;
    ArrayList<Medicamento> listaMedicamentos;
    EditListAdapter elaEditList;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

//        //Agregar custom ActionBar
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(getResources().getString(R.string.toolbar_title_edit));
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listaMedicamentos = SqlHelper.getInstance(this).getMedicamentos();
        lvEditList = (ListView) findViewById(R.id.lvEditList);
        elaEditList = new EditListAdapter(this, listaMedicamentos);

        lvEditList.setAdapter(elaEditList);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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


    public void openEditWindows(View v) {

        TextView tvId = (TextView) findViewById(R.id.tvId);
        int id = Integer.valueOf(tvId.getText().toString());

        Intent in = new Intent(this, EditFormActivity.class).
                putExtra("id", id);
        startActivity(in);

    }

}