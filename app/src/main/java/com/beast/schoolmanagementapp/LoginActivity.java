package com.beast.schoolmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText emailField, passwordField;
    private Button loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Check user role and redirect to respective dashboard
                            FirebaseUser user = mAuth.getCurrentUser();
                            checkUserRole(user);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void checkUserRole(FirebaseUser user) {
        String userId = user.getUid();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String role = snapshot.child("role").getValue(String.class);
                if (role.equals("admin")) {
                    startActivity(new Intent(LoginActivity.this, AdminDashboardActivity.class));
                } else if (role.equals("teacher")) {
                    startActivity(new Intent(LoginActivity.this, TeacherDashboardActivity.class));
                } else if (role.equals("student")) {
                    startActivity(new Intent(LoginActivity.this, StudentDashboardActivity.class));
                } else {
                    startActivity(new Intent(LoginActivity.this, ParentDashboardActivity.class));
                }
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}
