package com.undead.nosavvy.labcalificado1;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

public class NotificationUtils extends ContextWrapper {

    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "com.undead.nosavvy.labcalificado1";
    public static final String IOS_CHANNEL_ID = "com.undead.nosavvy.labcalificado1";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    public static final String IOS_CHANNEL_NAME = "IOS CHANNEL";

    public NotificationUtils(Context base) {
        super(base);
        createChannels();

        String groupId = "group_id_101";
        CharSequence groupName = "Channel Name";
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannelGroup(new NotificationChannelGroup(groupId, groupName));
    }

    public void createChannels() {

        // create android channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        // Sets whether notifications posted to this channel should display notification lights
        androidChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        androidChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        androidChannel.setLightColor(Color.RED);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        androidChannel.setShowBadge(true);
        getManager().createNotificationChannel(androidChannel);

        // create ios channel
        NotificationChannel iosChannel = new NotificationChannel(IOS_CHANNEL_ID,
                IOS_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        iosChannel.enableLights(true);
        iosChannel.enableVibration(true);
        iosChannel.setShowBadge(true);
        iosChannel.setLightColor(Color.RED);
        iosChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(iosChannel);
    }

    public Notification.Builder getAndroidChannelNotification(String title, String body, PendingIntent intent) {
        return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_pizza_slice)
                .setContentText(body)
                .setContentIntent(intent)
                .setAutoCancel(true);
    }

    public Notification.Builder getIosChannelNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), IOS_CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_pizza_slice)
                .setContentText(body)
                .setAutoCancel(true);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public void deleteNotificationChannel(String channelId) {

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.deleteNotificationChannel(channelId);
    }
}