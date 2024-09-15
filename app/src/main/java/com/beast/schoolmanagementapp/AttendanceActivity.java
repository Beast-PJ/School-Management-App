package com.beast.schoolmanagementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAttendance;
    private AttendanceAdapter attendanceAdapter;
    private List<Student> studentList;
    private Map<Integer, Boolean> attendanceStatus = new HashMap<>();
    private TextView tvTotalPresent, tvTotalAbsent, tvPresentPercentage;
    private Button submitAttendanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        // Initialize RecyclerView and other UI components
        recyclerViewAttendance = findViewById(R.id.recyclerViewAttendance);
        tvTotalPresent = findViewById(R.id.tv_total_present);
        tvTotalAbsent = findViewById(R.id.tv_total_absent);
        tvPresentPercentage = findViewById(R.id.tv_present_percentage);
        submitAttendanceButton = findViewById(R.id.submitAttendanceButton);

        // Set layout manager for grid format
        recyclerViewAttendance.setLayoutManager(new GridLayoutManager(this, 2));

        // Load students into the RecyclerView and set up adapter
        loadStudents();

        // Submit Attendance Button
        submitAttendanceButton.setOnClickListener(view -> submitAttendance());
    }

    private void loadStudents() {
        // Populate the student list (you may want to fetch this from a database)
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "John Doe", 1));
        studentList.add(new Student(2, "Jane Smith", 2));
        studentList.add(new Student(3, "Mike Johnson", 3));
        studentList.add(new Student(4, "Emma Brown", 4));

        // Initialize adapter with student list and attendance status
        attendanceAdapter = new AttendanceAdapter(studentList, attendanceStatus);
        recyclerViewAttendance.setAdapter(attendanceAdapter);

        // Update the attendance statistics
        updateAttendanceStats();
    }

    // Method to update the attendance statistics based on the checkboxes
    private void updateAttendanceStats() {
        int totalStudents = studentList.size();
        int presentCount = 0;

        // Count present students based on the attendanceStatus map
        for (int i = 0; i < studentList.size(); i++) {
            if (attendanceStatus.getOrDefault(i, false)) {
                presentCount++;
            }
        }

        int absentCount = totalStudents - presentCount;
        double presentPercentage = (totalStudents == 0) ? 0 : (presentCount * 100.0 / totalStudents);

        // Update the TextViews
        tvTotalPresent.setText(String.valueOf(presentCount) + " / " + totalStudents);
        tvTotalAbsent.setText(String.valueOf(absentCount));
        tvPresentPercentage.setText(String.format("%.2f%%", presentPercentage));
    }

    // Method to handle attendance submission
    private void submitAttendance() {
        // You can implement your logic to submit attendance data (e.g., saving to a database)

        // Show confirmation or perform any desired action after submission
    }
}
