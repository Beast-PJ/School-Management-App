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
import com.google.firebase.firestore.FirebaseFirestore;

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
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Start animations
        splashLogo.startAnimation(zoomIn);
        appName.startAnimation(fadeIn);

        // After SPLASH_DURATION, check the user role and redirect
        new Handler().postDelayed(() -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser == null) {
                startActivity(new Intent(SplashActivity.this, Login_Activity.class));
            } else {
                checkUserRole(currentUser.getUid());
            }
            finish();
        }, SPLASH_DURATION);
    }

    private void checkUserRole(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String role = documentSnapshot.getString("role");
                if (role != null) {
                    switch (role) {
                        case "admin":
                            startActivity(new Intent(SplashActivity.this, AdminDashboardActivity.class));
                            break;
                        case "teacher":
                            startActivity(new Intent(SplashActivity.this, TeacherDashboardActivity.class));
                            break;
                    }
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                finish();
            }
        }).addOnFailureListener(e -> {
            startActivity(new Intent(SplashActivity.this, Login_Activity.class));
            finish();
        });
    }
}
