package com.beast.schoolmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditTeacherActivity extends AppCompatActivity {

    private EditText editTeacherNameEditText;
    private EditText editTeacherPhoneEditText;
    private ImageView editTeacherAvatar;
    private int selectedAvatarResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teacher);

        editTeacherNameEditText = findViewById(R.id.editTeacherNameEditText);
        editTeacherPhoneEditText = findViewById(R.id.editTeacherPhoneEditText);
        editTeacherAvatar = findViewById(R.id.editTeacherAvatar);

        // Get data from intent
        Intent intent = getIntent();
        String teacherName = intent.getStringExtra("teacherName");
        String teacherPhone = intent.getStringExtra("teacherPhone");
        selectedAvatarResId = intent.getIntExtra("teacherAvatar", R.drawable.blue_gradient_background);

        // Set current data
        editTeacherNameEditText.setText(teacherName);
        editTeacherPhoneEditText.setText(teacherPhone);
        editTeacherAvatar.setImageResource(selectedAvatarResId);

        // Set click listener for avatar selection
        editTeacherAvatar.setOnClickListener(v -> {
            // Logic to choose a new avatar
        });
    }

    public void onUpdateTeacherClick(View view) {
        String name = editTeacherNameEditText.getText().toString().trim();
        String phone = editTeacherPhoneEditText.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create updated Teacher object
        Teacher updatedTeacher = new Teacher(name, phone, selectedAvatarResId);

        // Return updated teacher data
        Intent resultIntent = new Intent();
        resultIntent.putExtra("teacherName", updatedTeacher.getName());
        resultIntent.putExtra("teacherPhone", updatedTeacher.getPhoneNumber());
        resultIntent.putExtra("teacherAvatar", updatedTeacher.getAvatarResId());

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
