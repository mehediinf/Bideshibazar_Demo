package com.mtach.bideshibazar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);


        ImageView logo = findViewById(R.id.logo);
        TextView title = findViewById(R.id.title);
        TextView tagline = findViewById(R.id.tagline);

        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_bounce);
        Animation titleAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_fade_in);
        Animation taglineAnim = AnimationUtils.loadAnimation(this, R.anim.slide_in_zoom_fade);

        logo.startAnimation(logoAnim);
        title.startAnimation(titleAnim);
        tagline.startAnimation(taglineAnim);


        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 2500);
    }
}