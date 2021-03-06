package com.example.atmosfera.medicamentosapp;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.atmosfera.medicamentosapp.databases.SqlHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FrecuenciaActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button button_stpd;
    int hour_x, minute_x;
    TextView textHora;
    static final int DIALOD_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frecuencia);

        //Agregar custom ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.toolbar_title_frecuencia));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //TimePicker
        textHora = (TextView) findViewById(R.id.text_hora);
        showTimePickerDialog();

        //cargar Spinners
        setupSpinners();
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

    public void showTimePickerDialog() {
        button_stpd = (Button) findViewById(R.id.button_selec);
        button_stpd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOD_ID);
                    }
                }

        );
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOD_ID)
            return new TimePickerDialog(this, kTimePickerListener, hour_x, minute_x, true);
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hour_x = hourOfDay;
                    minute_x = minute;
                    String auxH = (hour_x < 10) ? "0" + hour_x : "" + hour_x;
                    String auxM = (minute_x < 10) ? "0" + minute_x : "" + minute_x;
                    textHora.setText(auxH + ":" + auxM);

                }
            };

    private void setupSpinners() {
        Spinner spinnerIntervalo = (Spinner) findViewById(R.id.spinner_intervalo);
        Spinner spinnerDuracion = (Spinner) findViewById(R.id.spinner_duracion);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterIntervalo = ArrayAdapter.createFromResource(this, R.array.intervalos_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterDuracion = ArrayAdapter.createFromResource(this, R.array.duracion_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapterIntervalo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterDuracion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerIntervalo.setAdapter(adapterIntervalo);
        spinnerDuracion.setAdapter(adapterDuracion);
    }

    public void anyadirMedicamento(View v) {
        if (textHora.length() != 0) {
            SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();

            //Añadir medicamento

            String nombre = getIntent().getStringExtra("nombre");
            int forma = getIntent().getIntExtra("forma", 0);
            String via = getIntent().getStringExtra("via");

            String horaPrimeraIngesta = ((TextView) findViewById(R.id.text_hora)).getText().toString();
            String fechaInicio = currentDate.format(today);

            int duracionPos = ((int) ((Spinner) findViewById(R.id.spinner_duracion)).getSelectedItemId());
            int intervaloPos = ((int) ((Spinner) findViewById(R.id.spinner_intervalo)).getSelectedItemId());

            String duraciones[] = getResources().getStringArray(R.array.duracion_array_values);
            String intervalos[] = getResources().getStringArray(R.array.intervalos_array_values);

            int duracion = Integer.valueOf(duraciones[duracionPos]);
            int intervalo = Integer.valueOf(intervalos[intervaloPos]);

            int res = SqlHelper.getInstance(this).addMedicamento(nombre, forma, via, horaPrimeraIngesta, fechaInicio, duracion, intervalo);

            if (res == -1) {
                Toast.makeText(FrecuenciaActivity.this, getResources().getString(R.string.msg_errror_add_medicament), Toast.LENGTH_LONG).show();
            } else {
                SqlHelper.getInstance(this).createAvisosMedicamento(res, horaPrimeraIngesta, fechaInicio, duracion, intervalo);
                Toast.makeText(FrecuenciaActivity.this, getResources().getString(R.string.msg_ok_add_medicament), Toast.LENGTH_LONG).show();

                createAlarm(res, horaPrimeraIngesta, (intervalo * 60 * 60 * 1000));

                Intent in = new Intent(this, MainActivity.class);
                in.setAction("hola");
                startActivity(in);
                finish();
            }

        } else
            Toast.makeText(FrecuenciaActivity.this, getResources().getString(R.string.campo_selecc_hora_vacio), Toast.LENGTH_LONG).show();
    }

    public void createAlarm(int id, String hora, int time) {

        Calendar calendar = Calendar.getInstance();
        //Cuando se dispara la primera alarma.
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hora.substring(0, 2)));
        calendar.set(Calendar.MINUTE, Integer.valueOf(hora.substring(3, 5)));
        calendar.set(Calendar.SECOND, 00);

//        Genera distintos IDs para tener múltiples alarmas
//        final int ID = (int) System.currentTimeMillis();

        Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
        intent.putExtra("texto", getResources().getString(R.string.textNotification));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), time, pendingIntent);

    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }
}
