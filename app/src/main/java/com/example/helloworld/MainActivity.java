package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
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
        //1
//        Intent shoeProfile = new Intent(MainActivity.this, ProfileDetails.class);
//        startActivity(shoeProfile);

        // 2
        Intent shoeProfile = new Intent(MainActivity.this, ProfileDetails.class);


        Pair[] pairs = new Pair[3];

        pairs[0] = new Pair<View, String>(mProfileImage, "imageTransistion");
        pairs[1] = new Pair<View, String>(mNameText, "nameTransistion");
        pairs[2] = new Pair<View, String>(mDescText, "descTransistion");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);

        startActivity(shoeProfile, options.toBundle());

    }

}