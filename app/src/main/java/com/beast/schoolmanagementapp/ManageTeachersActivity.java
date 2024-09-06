package com.beast.schoolmanagementapp;
package com.example.schoolmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ManageTeachersActivity extends AppCompatActivity {

    private RecyclerView teachersRecyclerView;
    private FloatingActionButton addTeacherFab;
    private TeacherAdapter teacherAdapter;
    private List<Teacher> teacherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_teachers);

        // Initialize Views
        teachersRecyclerView = findViewById(R.id.teachersRecyclerView);
        addTeacherFab = findViewById(R.id.addTeacherFab);

        // Set up RecyclerView
        teacherList = new ArrayList<>();
        teacherAdapter = new TeacherAdapter(teacherList, new TeacherAdapter.OnTeacherClickListener() {
            @Override
            public void onEditClick(Teacher teacher) {
                // Logic to edit teacher
                Intent intent = new Intent(ManageTeachersActivity.this, EditTeacherActivity.class);
                intent.putExtra("teacherName", teacher.getName());
                intent.putExtra("teacherSubject", teacher.getSubject());
                intent.putExtra("teacherEmail", teacher.getEmail());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(Teacher teacher) {
                // Logic to delete teacher
                teacherList.remove(teacher);
                teacherAdapter.notifyDataSetChanged();
            }
        });
        teachersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teachersRecyclerView.setAdapter(teacherAdapter);

        // Load Teachers (dummy data for now)
        loadTeachers();

        // FAB onClick Listener to add a new teacher
        addTeacherFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddTeacherActivity to add a new teacher
                Intent intent = new Intent(ManageTeachersActivity.this, AddTeacherActivity.class);
                startActivity(intent);
            }
        });
    }

    // Load Teachers (Dummy Data)
    private void loadTeachers() {
        // Dummy data for demonstration purposes
        teacherList.add(new Teacher("John Doe", "Math", "johndoe@gmail.com"));
        teacherList.add(new Teacher("Jane Smith", "Science", "janesmith@gmail.com"));
        teacherAdapter.notifyDataSetChanged();
    }
}
