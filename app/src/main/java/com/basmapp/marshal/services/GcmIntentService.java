package com.basmapp.marshal.services;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.basmapp.marshal.Constants;
import com.basmapp.marshal.ui.MainActivity;
import com.basmapp.marshal.ui.utils.NotificationUtils;
import com.google.android.gms.gcm.GcmListenerService;

public class GcmIntentService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        Log.i("GCM", "onMessageReceived from:" + from);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        NotificationUtils mNotificationUtils = new NotificationUtils(this);

        Uri ringtoneUri = mNotificationUtils.getRingtoneUri();
        long[] vibrate = mNotificationUtils.getVibrate();
        int lightColor = mNotificationUtils.getLightColor();

//        long[] vibrate = new long[]{0};
//        if (sharedPreferences.getBoolean(Constants.PREF_NOTIFICATIONS_NEW_MESSAGE, false)) {
//            vibrate = new long[]{0,1000};
//        }

//        String ringtonePref = sharedPreferences.getString(Constants.PREF_NOTIFICATIONS_NEW_RINGTONE, null);
//        Uri ringtoneUri;
//        if (ringtonePref != null) {
//            ringtoneUri = Uri.parse(ringtonePref);
//        } else {
//            ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }

//        int lightColor = Color.parseColor(sharedPreferences
//                .getString(Constants.PREF_NOTIFICATIONS_COLOR, "#FFFFFF"));

        String message = data.getString("message");
        if (message != null &&
                message.equals("set-registration-state=false")) {
            GcmRegistrationService.setDeviceRegistrationState(this, false);
        } else if (message != null && message.equals("data-update-true")) {
            Log.i("GCM", "data-update-true");
            UpdateIntentService.startUpdateData(this);
        } else if (message != null && message.equals("reset-gcm-registration-pref")) {
            sharedPreferences.edit().putBoolean(Constants.PREF_IS_DEVICE_REGISTERED, false).apply();
        } else if (message != null && message.equals("show-must-update-dialog")) {
            sharedPreferences.edit().putBoolean(Constants.PREF_MUST_UPDATE, true).apply();
        } else if (message != null && message.contains("show-picture-style-notification")
                && sharedPreferences.getBoolean("notifications_new_message", true)) {
            String[] separated = message.split(";");

            PendingIntent notifyPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                    MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

            new NotificationUtils.GeneratePictureStyleNotification(this, separated[1].trim(), separated[2].trim(),
                    vibrate, ringtoneUri, lightColor, notifyPendingIntent).execute();
        } else {
            if (sharedPreferences.getBoolean(Constants.PREF_NOTIFY_NEW_MESSAGE, true)) {
                PendingIntent notifyPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                        MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

                mNotificationUtils.notify(message, notifyPendingIntent);
            }
        }
    }
}
