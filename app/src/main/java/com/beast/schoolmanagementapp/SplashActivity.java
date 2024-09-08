package com.beast.schoolmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import com.beast.schoolmanagementapp.User.Login_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // 3 seconds
    private ProgressBar progressBarSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView splashLogo = findViewById(R.id.splashLogo);
        TextView appName = findViewById(R.id.appName);
        progressBarSplash = findViewById(R.id.progressBarSplash);

        // Load animations
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);

        // Start the zoom in animation
        splashLogo.startAnimation(zoomIn);

        // Start the zoom out animation when zoom in finishes
        zoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                splashLogo.startAnimation(zoomOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        // After the SPLASH_DURATION, transition to Login Activity
        new Handler().postDelayed(() -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if(currentUser==null){
                startActivity(new Intent(SplashActivity.this, Login_Activity.class));
            }
            else{
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
            finish();
        }, 1000);
    }
}
