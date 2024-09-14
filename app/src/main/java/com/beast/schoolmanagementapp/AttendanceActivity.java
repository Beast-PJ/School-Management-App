// AttendanceActivity.java
package com.beast.schoolmanagementapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    private TextView txtHeader;
    private ListView listViewStudents;
    private Button btnSubmit;
    private FirebaseFirestore db;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        txtHeader = findViewById(R.id.txt_header);
        listViewStudents = findViewById(R.id.list_view_students);
        btnSubmit = findViewById(R.id.btn_submit);
        db = FirebaseFirestore.getInstance();
        studentList = new ArrayList<>();

        // Get the selected standard, class, time period, and date
        String selectedStandard = getIntent().getStringExtra("standard");
        String selectedClass = getIntent().getStringExtra("class");
        String selectedTimePeriod = getIntent().getStringExtra("timePeriod");
        String selectedDate = getIntent().getStringExtra("date");

        txtHeader.setText("Attendance for " + selectedStandard + " " + selectedClass + " on " + selectedDate + " (" + selectedTimePeriod + ")");

        // Fetch students from Firestore, ordered by roll number
        fetchStudents(selectedStandard, selectedClass);
    }

    private void fetchStudents(String standard, String classSection) {
        CollectionReference studentsRef = db.collection("students");
        Query query = studentsRef.whereEqualTo("standard", standard)
                .whereEqualTo("class", classSection)
                .orderBy("rollNumber");

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Student student = document.toObject(Student.class);
                    studentList.add(student);
                }

                // Populate the ListView with students
                studentAdapter = new StudentAdapter(AttendanceActivity.this, studentList);
                listViewStudents.setAdapter((ListAdapter) studentAdapter);
            } else {
                Toast.makeText(AttendanceActivity.this, "Error fetching students", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
