package com.example.tareapreferencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

Button definir;
Button recuperar;
Button notificacion;
private NotificationManager notificador;
String CHANNEL_ID = "8";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        notificador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("Mensaje de alerta")
                .setContentText("Ejemplo de notificación")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setTicker("AVISO DE NOTIFICACIONES");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(resultPendingIntent);

        builder.setAutoCancel(true);

        definir = (Button) findViewById(R.id.buttonDefinir);
        definir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DefinirActivity.class);
                startActivity(i);
            }
        });

        recuperar = (Button) findViewById(R.id.buttonRecuperar);
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                Log.i("", "El sistema operativo es unico: " + sp.getBoolean("clave1", false));
                Log.i("", "Sistema operativo: " + sp.getString("clave2", "No asignada"));
                Log.i("", "Versión: " + sp.getString("clave3", "No asignada"));
            }
        });


        notificacion = (Button) findViewById(R.id.buttonNoti);
        notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Notification n = builder.build();
                builder.setFullScreenIntent(resultPendingIntent, true);
                notificador.notify(1,n);






            }
        });
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.chanel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



}