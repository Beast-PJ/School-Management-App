package com.beast.schoolmanagementapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AttendanceAdapter attendanceAdapter;
    private Button submitAttendanceButton;
    private List<Student> studentList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        recyclerView = findViewById(R.id.recyclerViewAttendance);
        submitAttendanceButton = findViewById(R.id.submitAttendanceButton);

        // Initialize the student list with data (this should be fetched from your database)
        studentList = new ArrayList<>();
        // Example data
        studentList.add(new Student(1, "John", "A.", "Doe"));
        studentList.add(new Student(2, "Jane", "B.", "Smith"));
        // Add more students as needed

        // Set up the RecyclerView with a GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2 columns
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set up the adapter
        attendanceAdapter = new AttendanceAdapter(studentList);
        recyclerView.setAdapter(attendanceAdapter);

        // Handle attendance submission
        submitAttendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray attendanceStatus = attendanceAdapter.getAttendanceStatus();
                for (int i = 0; i < attendanceStatus.size(); i++) {
                    int position = attendanceStatus.keyAt(i);
                    boolean isPresent = attendanceStatus.get(position);
                    Student student = studentList.get(position);
                    // Save attendance data for each student based on their attendance status
                    // Here you would typically store it in a database
                    Toast.makeText(AttendanceActivity.this, "Roll No: " + student.getRollNumber() +
                            " is " + (isPresent ? "Present" : "Absent"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
