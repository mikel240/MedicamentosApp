package com.example.atmosfera.medicamentosapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.atmosfera.medicamentosapp.adapters.ViewPagerAdapter;
import com.example.atmosfera.medicamentosapp.fragments.DashboardFragment;
import com.example.atmosfera.medicamentosapp.fragments.RegistroFragment;
import com.example.atmosfera.medicamentosapp.fragments.SettingsFragment;
import com.example.atmosfera.medicamentosapp.fragments.TomarHoyFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Agregar custom ActionBar
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DashboardFragment(), getResources().getString(R.string.tab_dashboard_title));
        adapter.addFragment(new TomarHoyFragment(), getResources().getString(R.string.tab_tomarHoy_title));
        adapter.addFragment(new RegistroFragment(), getResources().getString(R.string.tab_registro_title));
        adapter.addFragment(new SettingsFragment(), getResources().getString(R.string.tab_config_title));
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        //tab dashboard
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(getResources().getString(R.string.tab_dashboard_title));
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.icon_add_selector, 0, 0);
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

        //tab ajustes
        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText(getResources().getString(R.string.tab_config_title));
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon_settings_selector, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);

        //Por defecto la tab del dashboard está selected (soluciona problemas del selector, ya que la primera tab
        //no cambiaba de color)
        tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }
}
