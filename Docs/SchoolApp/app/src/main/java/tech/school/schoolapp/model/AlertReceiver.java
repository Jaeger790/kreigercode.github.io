package tech.school.schoolapp.model;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import tech.school.schoolapp.R;

public class AlertReceiver extends BroadcastReceiver {
    String channelID = "assessment";
    static int notificationID;

        @Override
        public void onReceive(Context context, Intent intent) {
            createNotificationChannel(context,channelID);
            Notification newNotification = new NotificationCompat.Builder(context,channelID).setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText(intent.getStringExtra("key"))
                    .setContentTitle("Assessment Notification").build();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationID++,newNotification);
        }

        private void createNotificationChannel(Context context,String channel_ID){
            CharSequence name = context.getResources().getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channel_ID,name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

}
