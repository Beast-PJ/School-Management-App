package com.beast.schoolmanagementapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddTeacherActivity extends AppCompatActivity {

    private EditText teacherNameEditText;
    private EditText teacherPhoneEditText;
    private ImageView teacherAvatar;
    private int selectedAvatarResId = R.drawable.blue_gradient_background; // Default avatar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        teacherNameEditText = findViewById(R.id.teacherNameEditText);
        teacherPhoneEditText = findViewById(R.id.teacherPhoneEditText);
        teacherAvatar = findViewById(R.id.teacherAvatar);

        // Set click listener for avatar selection
        teacherAvatar.setOnClickListener(v -> {
            // Logic to choose avatar can be added here (e.g., open image picker)
        });
    }

    public void onSaveTeacherClick(View view) {
        String name = teacherNameEditText.getText().toString().trim();
        String phone = teacherPhoneEditText.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Teacher object
        Teacher newTeacher = new Teacher(name, phone, selectedAvatarResId);

        // Send back the result to the calling activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("teacherName", newTeacher.getName());
        resultIntent.putExtra("teacherPhone", newTeacher.getPhoneNumber());
        resultIntent.putExtra("teacherAvatar", newTeacher.getAvatarResId());

        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
