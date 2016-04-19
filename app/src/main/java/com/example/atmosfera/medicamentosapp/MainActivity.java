package com.example.atmosfera.medicamentosapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.atmosfera.medicamentosapp.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        precargarToolbar();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        precargarTabLayout();

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void precargarTabLayout() {
        final TabLayout.Tab dashboard = tabLayout.newTab();
        final TabLayout.Tab tomarHoy = tabLayout.newTab();
        final TabLayout.Tab registro = tabLayout.newTab();
        final TabLayout.Tab settings = tabLayout.newTab();

        dashboard.setText(R.string.tab_dashboard_name);
        tomarHoy.setText(R.string.tab_tomarHoy_name);
        registro.setText(R.string.tab_registro_name);
        settings.setText(R.string.tab_config_name);

        tabLayout.addTab(dashboard, 0);
        tabLayout.addTab(tomarHoy, 1);
        tabLayout.addTab(registro, 2);
        tabLayout.addTab(settings, 3);
    }

    private void precargarToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Volver atrás en el Toolbar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
