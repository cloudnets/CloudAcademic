package com.cloudnets.cloudacademic.ApiService;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import com.cloudnets.cloudacademic.R;
import com.cloudnets.cloudacademic.Views.ListaEstudiantes;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMIntentService extends IntentService {

    private static final int NOTIF_ALERTA_ID = 1;
    public GCMIntentService() {
        super("GCMIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messaageType = gcm.getMessageType(intent);
        Bundle extras = intent.getExtras();
        if(!extras.isEmpty()){
            if(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messaageType)){
                onMessage();
            }
        }
        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void onMessage() {
        String msg = getString(R.string.notificacion_mensaje);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getString(R.string.notificacion_titulo))
                        .setContentText(msg);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notIntent =  new Intent(this, ListaEstudiantes.class);
        notIntent.putExtra("push", true);
        PendingIntent contIntent = PendingIntent.getActivity(this, 0, notIntent, 0);
        mBuilder.setContentIntent(contIntent);

        mBuilder.setSound(alarmSound);
        mBuilder.setAutoCancel(true);
        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        mNotificationManager.notify(NOTIF_ALERTA_ID, notification);
    }

}
