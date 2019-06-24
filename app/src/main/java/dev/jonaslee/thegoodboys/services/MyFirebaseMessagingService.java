package dev.jonaslee.thegoodboys.services;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import dev.jonaslee.thegoodboys.MainActivity;
import dev.jonaslee.thegoodboys.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "FROM : " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0){
            Log.d(TAG, "Message data : " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null){
            Log.d(TAG, "msg body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }

    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void send(String asd){
        this.sendNotification(asd);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotification(String body) {

            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel notificationChannel = new NotificationChannel("quartermaster", "quartermaster", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{300, 400, 500, 400, 300});
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent =  PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("NOVA COMPRA !!!")
                .setContentText("Arraste para mostrar detalhes!")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.jogador1)
                .setSound(notificationSound)
                .setChannelId("quartermaster")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(body))
                .setContentIntent(pendingIntent);

            notificationManager.notify(0, notifiBuilder.build());
    }
}
