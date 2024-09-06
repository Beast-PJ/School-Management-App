package com.beast.schoolmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminDashboardActivity extends AppCompatActivity {

    private CardView manageTeachersCard, manageStudentsCard, manageEventsCard, viewReportsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize Cards
        manageTeachersCard = findViewById(R.id.manageTeachersCard);
        manageStudentsCard = findViewById(R.id.manageStudentsCard);
        manageEventsCard = findViewById(R.id.manageEventsCard);
        viewReportsCard = findViewById(R.id.viewReportsCard);

        // Set onClick listeners
        manageTeachersCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, ManageTeachersActivity.class);
                startActivity(intent);
            }
        });

//        manageStudentsCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminDashboardActivity.this, ManageStudentsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        manageEventsCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminDashboardActivity.this, ManageEventsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        viewReportsCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminDashboardActivity.this, ViewReportsActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
