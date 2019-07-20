package uzi.utm.hellofirebase.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

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
    }
}
