package com.beast.schoolmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

public class TeacherDashboardActivity extends AppCompatActivity {

    private CardView cardAttendance, cardPerformance, cardTimetable, cardMessages,cardAddStudent,cardUpdateStudent;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        // Initialize views
        cardAttendance = findViewById(R.id.cardAttendance);
        cardPerformance = findViewById(R.id.cardPerformance);
        cardAddStudent = findViewById(R.id.cardAddStudent);
        cardUpdateStudent = findViewById(R.id.cardUpdateStudent);
        cardTimetable = findViewById(R.id.cardTimetable);
        cardMessages = findViewById(R.id.cardMessages);

        // Set OnClickListeners with animation
        cardAttendance.setOnClickListener(v -> {
            animateCard(v);
            openAttendance();
        });

        cardPerformance.setOnClickListener(v -> {
            animateCard(v);
//            openPerformance();
        });

        cardTimetable.setOnClickListener(v -> {
            animateCard(v);
//            openTimetable();
        });

        cardMessages.setOnClickListener(v -> {
            animateCard(v);
//            openMessages();
        });
        cardAddStudent.setOnClickListener(v -> {
            animateCard(v);
            openAddStudent();
        });

        cardMessages.setOnClickListener(v -> {
            animateCard(v);
//            open();
        });
    }

    // Method to handle card animation
    private void animateCard(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0.95f, 1.0f, 0.95f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(150);
        scaleAnimation.setFillAfter(true);
        view.startAnimation(scaleAnimation);
    }

    // Method to set up the drawer and toggle


//     Method to open Attendance screen
    private void openAttendance() {
        Intent intent = new Intent(TeacherDashboardActivity.this, AttendanceActivity.class);
        startActivity(intent);
    }

//    // Method to open Performance screen
//    private void openPerformance() {
//        Intent intent = new Intent(TeacherDashboardActivity.this, PerformanceActivity.class);
//        startActivity(intent);
//    }

    // Method to open Timetable screen
//    private void openTimetable() {
//        Intent intent = new Intent(TeacherDashboardActivity.this, TimetableActivity.class);
//        startActivity(intent);
//    }

//    private void openMessages() {
//        Intent intent = new Intent(TeacherDashboardActivity.this, MessagesActivity.class);
//        startActivity(intent);
//    }

    private void openAddStudent() {
        Intent intent = new Intent(TeacherDashboardActivity.this, AddStudentActivity.class);
        startActivity(intent);
    }
//
//    private void openUpdateStudent() {
//        Intent intent = new Intent(TeacherDashboardActivity.this, UpdateStudentActivity.class);
//        startActivity(intent);
//    }

    // Override onOptionsItemSelected to handle navigation
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
