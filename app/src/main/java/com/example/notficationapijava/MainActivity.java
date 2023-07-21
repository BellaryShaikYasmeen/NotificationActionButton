package com.example.notficationapijava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Notification;
import android.view.View;
import android.widget.EditText;

import static com.example.notficationapijava.App.CHANNEL_1_ID;
import static com.example.notficationapijava.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);
    }

    public void sendOnChannel1(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, activityIntent, 0);
        Intent boardcastintent = new Intent(this, BoardCastRe.class);
        boardcastintent.putExtra("toastMessage", message);
        PendingIntent actionintent = PendingIntent.getBroadcast(this, 0, boardcastintent, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.facesimleemoji);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.baseline_cloud_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle().setSummaryText("Summary text")
                        .setBigContentTitle("Enclopideia")
                        .bigText(getString(R.string.long_txt)))
                .setColor(Color.BLUE)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .setContentIntent(pi)
                .addAction(R.mipmap.ic_launcher, "Reply", actionintent)
                .build();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.baseline_cloud_done_24)
                .setStyle(new NotificationCompat.InboxStyle().
                        addLine("this is line 1")
                        .addLine("this is line 2")
                        .addLine("this is line 3")
                        .addLine("this is line 4")
                        .addLine("this is line 5")
                        .addLine("this is line 6")
                        .addLine("this is line 7")
                        .addLine("this is line 8")
                        .addLine("this is line 9")
                        .addLine("this is line 10")
                        .addLine("this is line 11")
                        .addLine("this is line 12")
                        .addLine("this is line 13")
                        .setSummaryText("Summary text")
                        .setBigContentTitle("Enclopideia")
                )
                .setContentTitle(title)
                .setContentText(message + "Second Channel")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(2, notification);
    }
}