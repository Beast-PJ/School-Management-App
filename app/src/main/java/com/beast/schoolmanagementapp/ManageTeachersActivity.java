package com.beast.schoolmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
                intent.putExtra("teacherPhone", teacher.getPhoneNumber());
                intent.putExtra("teacherAvatar", teacher.getAvatarResId());
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

        // Load Teachers
        loadTeachers();

        // Floating Action Button to Add a New Teacher
        addTeacherFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddTeacherActivity
                Intent intent = new Intent(ManageTeachersActivity.this, AddTeacherActivity.class);
                startActivity(intent);
            }
        });
    }

    // Load Teachers (Dummy Data)
    private void loadTeachers() {
        teacherList.add(new Teacher("John Doe", "+123456789", R.drawable.blue_gradient_card));
        teacherList.add(new Teacher("Jane Smith", "+987654321", R.drawable.blue_gradient_background));
        teacherList.add(new Teacher("Mark Taylor", "+1122334455", R.drawable.blue_gradient_header));
        teacherAdapter.notifyDataSetChanged();
    }
}
