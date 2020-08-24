package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.util.Pair;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mProfileImage;
    private TextView mNameText, mDescText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProfileImage = findViewById(R.id.image_profile);
        mNameText = findViewById(R.id.profile_name);
        mDescText = findViewById(R.id.profile_desc);
    }

    public void showDetailView(View view){


        NotificationCompat.Builder mBuilder= new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Notification Alert")
                .setAutoCancel(true)
                .setContentText("This is test notification")
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0));
        NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mBuilder.build());


        // 2
//        Intent shoeProfile = new Intent(MainActivity.this, ProfileDetails.class);
//        Pair[] pairs = new Pair[3];
//        pairs[0] = new Pair<View, String>(mProfileImage, "imageTransistion");
//        pairs[1] = new Pair<View, String>(mNameText, "nameTransistion");
//        pairs[2] = new Pair<View, String>(mDescText, "descTransistion");
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
//        startActivity(shoeProfile, options.toBundle());

    }

}