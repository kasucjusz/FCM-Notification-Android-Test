package com.kasucjusz.fcmreceivingdata;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService  {

    private static final String TAG = "TAG";

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d("TAG", "Token refreshed:"+ token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d("TAG", "Message received: "+remoteMessage);

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Map<String, String> data = remoteMessage.getData();
            String score = data.get("score");
            Log.d(TAG, score);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "DEFAULT_NOTIFICATION_CHANEL")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(score)
                    .setContentText("test description")
                    .setPriority(NotificationCompat.PRIORITY_MAX);

            NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = builder.build();

            if (notificationManager != null) {
                notificationManager.notify(new Random().nextInt(), notification);
            }

        }

    }
}
