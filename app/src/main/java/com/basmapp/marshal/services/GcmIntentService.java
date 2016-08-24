package com.basmapp.marshal.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.basmapp.marshal.Constants;
import com.basmapp.marshal.R;
import com.basmapp.marshal.ui.MainActivity;
import com.google.android.gms.gcm.GcmListenerService;


public class GcmIntentService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        Log.i("GCM", "onMessageReceived from:" + from);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String message = data.getString("message");
        if(message != null &&
                message.equals("set-registration-state=false")){
            GcmRegistrationService.setDeviceRegistrationState(this, false);
        } else if(message != null && message.equals("data-update-true")) {
            Log.i("GCM","data-update-true");
            UpdateIntentService.startUpdateData(this);
        } else if(message != null && message.equals("reset-gcm-registration-pref")) {
            sharedPreferences.edit().putBoolean(Constants.PREF_IS_DEVICE_REGISTERED, false).apply();
        } else if(message != null && message.equals("show-must-update-dialog")) {
            sharedPreferences.edit().putBoolean(Constants.PREF_MUST_UPDATE, true).apply();
        } else {
            if (sharedPreferences.getBoolean("notifications_new_message", true)) {
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                        MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

                long[] vibrate = new long[]{0};
                if(sharedPreferences.getBoolean("notifications_new_message_vibrate",false)) {
                    vibrate = new long[]{0,1000};
                }

                String ringtonePref = sharedPreferences.getString("notifications_new_message_ringtone", null);
                Uri ringtoneUri;
                if (ringtonePref != null) {
                    ringtoneUri = Uri.parse(ringtonePref);
                } else {
                    ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                }

                NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification.Builder notif = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.stat_notify_basmapp)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(message)
                        .setStyle(new Notification.BigTextStyle()
                                .bigText(message))
                        .setLights(Color.CYAN, 1000, 1000)
                        .setSound(ringtoneUri)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setVibrate(vibrate);
                notificationManager.notify(1, notif.build());
            }
        }
    }
}
