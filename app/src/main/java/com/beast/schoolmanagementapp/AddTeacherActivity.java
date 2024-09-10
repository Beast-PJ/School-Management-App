package com.beast.schoolmanagementapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddTeacherActivity extends AppCompatActivity {

    private EditText teacherEmail, teacherName;
    private Button addTeacherBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        teacherEmail = findViewById(R.id.teacher_email);
        teacherName = findViewById(R.id.teacher_name);
        addTeacherBtn = findViewById(R.id.add_teacher_btn);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        addTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher();
            }
        });
    }

    private void addTeacher() {
        String email = teacherEmail.getText().toString().trim();
        String name = teacherName.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            teacherEmail.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(name)) {
            teacherName.setError("Name is required");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // Store teacher data in Firestore
        Map<String, Object> teacher = new HashMap<>();
        teacher.put("name", name);
        teacher.put("email", email);

        // Add teacher to Firestore and send password reset email
        db.collection("teachers").add(teacher)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        sendPasswordResetEmail(email);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        progressBar.setVisibility(View.GONE);
                        // Show error message
                    }
                });
    }

    private void sendPasswordResetEmail(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Success message (teacher gets email for setting password)
            } else {
                // Error message handling
                if (task.getException() instanceof FirebaseAuthInvalidUserException || task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid email format or user does not exist
                }
            }
            progressBar.setVisibility(View.GONE);
        });
    }
}
