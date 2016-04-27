package com.example.atmosfera.medicamentosapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;


public class Notification_receiver extends BroadcastReceiver {

    int count = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        //Recogemos los parámetros pasados en FrecuenciaActivity
        String text = intent.getStringExtra("texto");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent reapeating_intent = new Intent(context, MainActivity.class);
        reapeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Para poder saber en el Main si venimos de una notificación
        reapeating_intent.setAction("open_tab_tomar_hoy");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, reapeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.icon_capsule)
                .setContentTitle("CapsulaApp")
                .setContentText(text)
                .setAutoCancel(true);

        //Vibración
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        notificationManager.notify(100, builder.build());


    }
}
