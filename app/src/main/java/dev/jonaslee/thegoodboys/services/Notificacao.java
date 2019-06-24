package dev.jonaslee.thegoodboys.services;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;

import dev.jonaslee.thegoodboys.R;

public class Notificacao {
    private String body;
    private Activity act;
    private ArrayList extras = null;
    private ArrayList nomes = null;
    private Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notificacao(String body, Activity act, ArrayList<String> nomes, ArrayList extras, Intent intent) {
        this.body = body;
        this.act = act;
        this.extras = extras;
        this.nomes = nomes;
        this.intent = intent;
        this.sendNot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNot() {
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new NotificationChannel("quartermaster", "quartermaster", importance);
        notificationChannel.enableLights(true);
        notificationChannel.setImportance(importance);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{300, 400, 500, 400, 300});
        NotificationManager notificationManager = (NotificationManager)act.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        int i = 0;
        if (!nomes.isEmpty()){
            while (nomes.size() > i){
                intent.putExtra(nomes.get(i).toString(), extras.get(i).toString());
                i++;
            }
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bitmap myLogo = BitmapFactory.decodeResource(act.getResources(), R.drawable.jogador1);
        PendingIntent pendingIntent =  PendingIntent.getActivity(act, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(act)
                .setContentTitle("!!!")
                .setContentText("Arraste para mostrar detalhes!")
                .setAutoCancel(true)
                .setPriority(999999)
                .setLargeIcon(myLogo)
                .setSmallIcon(R.drawable.ic_menu_send)
                .setSound(notificationSound)
                .setChannelId("quartermaster")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(body))
                .setContentIntent(pendingIntent);
        notificationManager.notify(0, notifiBuilder.build());
    }
}