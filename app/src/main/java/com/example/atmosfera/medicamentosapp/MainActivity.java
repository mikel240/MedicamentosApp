package com.example.atmosfera.medicamentosapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.atmosfera.medicamentosapp.adapters.ViewPagerAdapter;
import com.example.atmosfera.medicamentosapp.fragments.DashboardFragment;
import com.example.atmosfera.medicamentosapp.fragments.RegistroFragment;
import com.example.atmosfera.medicamentosapp.fragments.SettingsFragment;
import com.example.atmosfera.medicamentosapp.fragments.TomarHoyFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Agregar custom ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DashboardFragment(),getResources().getString(R.string.tab_dashboard_title));
        adapter.addFragment(new TomarHoyFragment(),getResources().getString(R.string.tab_tomarHoy_title));
        adapter.addFragment(new RegistroFragment(),getResources().getString(R.string.tab_registro_title));
        adapter.addFragment(new SettingsFragment(),getResources().getString(R.string.tab_config_title));
        viewPager.setAdapter(adapter);
    }
}
