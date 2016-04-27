package com.example.atmosfera.medicamentosapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.atmosfera.medicamentosapp.adapters.ViewPagerAdapter;
import com.example.atmosfera.medicamentosapp.databases.SqlHelper;
import com.example.atmosfera.medicamentosapp.fragments.DashboardFragment;
import com.example.atmosfera.medicamentosapp.fragments.RegistroFragment;
import com.example.atmosfera.medicamentosapp.fragments.TomarHoyFragment;
import com.example.atmosfera.medicamentosapp.pojo.Aviso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ArrayList<Aviso> listaAvisosTomarHoy;
    private ArrayList<Aviso> listaAvisosRegistro;


    public ArrayList<Aviso> getListaAvisosTomarHoy() {
        return listaAvisosTomarHoy;
    }

    public void setListaAvisosTomarHoy(ArrayList<Aviso> listaAvisosTomarHoy) {
        this.listaAvisosTomarHoy = listaAvisosTomarHoy;
    }

    public ArrayList<Aviso> getListaAvisosRegistro() {
        return listaAvisosRegistro;
    }

    public void setListaAvisosRegistro(ArrayList<Aviso> listaAvisosRegistro) {
        this.listaAvisosRegistro = listaAvisosRegistro;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Agregar custom ActionBar
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));//vasil
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int num = tab.getPosition();
                        //tabLayout.getTabAt(num).setCustomView(tabThree);
                        viewPager.setCurrentItem(num);


                    }
                }

        );

        listaAvisosTomarHoy = SqlHelper.getInstance(this).getAvisosHoy();
        listaAvisosRegistro = SqlHelper.getInstance(this).getAvisos();

        if (getIntent().getAction().equals("open_tab_tomar_hoy")) {
            viewPager.setCurrentItem(1);

        } else if (getIntent().getAction().equals("hola")) {
            viewPager.setCurrentItem(0);
        }
    }


    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new DashboardFragment(), getResources().getString(R.string.tab_dashboard_title));
        adapter.addFragment(new TomarHoyFragment(), getResources().getString(R.string.tab_tomarHoy_title));
        adapter.addFragment(new RegistroFragment(), getResources().getString(R.string.tab_registro_title));

        viewPager.setAdapter(adapter);

    }

    private void setupTabIcons() {
        //tab dashboard
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(getResources().getString(R.string.tab_dashboard_title));
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_add_selector, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        //tab tomarHoy
        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(getResources().getString(R.string.tab_tomarHoy_title));
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_capsule_selector, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        //tab registro
        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(getResources().getString(R.string.tab_registro_title));
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_diary_selector, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        //Por defecto la tab del dashboard está selected (soluciona problemas del selector, ya que la primera tab
        //no cambiaba de color)
        tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

}
