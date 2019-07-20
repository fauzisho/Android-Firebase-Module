package uzi.utm.hellofirebase.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import uzi.utm.hellofirebase.App;
import uzi.utm.hellofirebase.BuildConfig;
import uzi.utm.hellofirebase.R;

public class FirebaseService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseService";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("Firebase", "NEW TOKEN " + s);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String nChannelID = BuildConfig.APPLICATION_ID + ".notification.channel";

        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel nChannel;
            nChannel = new NotificationChannel(nChannelID, "TentorQ Notification", NotificationManager.IMPORTANCE_HIGH);
            if (nManager != null) {
                nManager.createNotificationChannel(nChannel);
            }
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(App.getInstance(), nChannelID)
                .setAutoCancel(true)
//                            .setContentIntent(resultIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentText(remoteMessage.getNotification().getBody())
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()));

        if (Build.VERSION.SDK_INT >= 24) {
            mBuilder.setPriority(NotificationManager.IMPORTANCE_HIGH);
            mBuilder.setPriority(NotificationManager.IMPORTANCE_MAX);
        }

        if (nManager != null) nManager.notify(11011, mBuilder.build());

    }
}
