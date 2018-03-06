package com.blazeey.sms;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "0";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.v("ACTION",intent.getAction());
        Bundle bundle = intent.getExtras();

        Object[] objects = (Object[]) bundle.get("pdus");

        String messageBody = "",phNo = "";
        for(int i = 0;i<objects.length;i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) objects[i]);
            messageBody = smsMessage.getDisplayMessageBody();
            phNo = smsMessage.getDisplayOriginatingAddress();

            Log.v("MESSAGE",messageBody+phNo);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_mail_black_24dp)
                .setContentTitle(phNo)
                .setContentText(messageBody)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        int importance = NotificationManager.IMPORTANCE_HIGH;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Message", importance);
            notificationChannel.setDescription("Messgage");
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);

            manager.notify(0, notification.build());
        }
    }


}
