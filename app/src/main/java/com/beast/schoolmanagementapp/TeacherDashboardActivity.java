package com.beast.schoolmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TeacherDashboardActivity extends AppCompatActivity {

    private CardView attendanceCard, assignmentsCard, marksCard, profileCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        // Initialize the card views
        attendanceCard = findViewById(R.id.attendanceCard);
        assignmentsCard = findViewById(R.id.assignmentsCard);
        marksCard = findViewById(R.id.marksCard);
        profileCard = findViewById(R.id.profileCard);

        // Set onClickListeners for each card
        attendanceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherDashboardActivity.this, AttendanceActivity.class);
                startActivity(intent);
            }
        });

        assignmentsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherDashboardActivity.this, AssignmentsActivity.class);
                startActivity(intent);
            }
        });

        marksCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherDashboardActivity.this, MarksActivity.class);
                startActivity(intent);
            }
        });

        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherDashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
