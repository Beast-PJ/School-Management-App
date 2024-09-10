package com.beast.schoolmanagementapp.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.beast.schoolmanagementapp.AdminDashboardActivity;
import com.beast.schoolmanagementapp.R;
import com.beast.schoolmanagementapp.TeacherDashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Login_Activity extends AppCompatActivity {

    EditText emailedittxt, passedittxt;
    Button login_btn;
    ProgressBar progressBar;
    CardView cardView, cardView2;
    LinearLayout linearLayout;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        emailedittxt = findViewById(R.id.emailedittxt);
        passedittxt = findViewById(R.id.passedittxt);
        login_btn = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progressbar);
        cardView = findViewById(R.id.cardView);
        cardView2 = findViewById(R.id.cardView2);
        linearLayout = findViewById(R.id.toplinearLayout);

        login_btn.setOnClickListener(view -> loginUser());
    }

    private void loginUser() {
        String email = emailedittxt.getText().toString();
        String password = passedittxt.getText().toString();

        if (!validateData(email, password)) return;

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                    // Fetch user role from Firestore
                    db.collection("users").document(userId).get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task1.getResult();
                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                String role = documentSnapshot.getString("role");
                                if ("admin".equals(role)) {
                                    startActivity(new Intent(Login_Activity.this, AdminDashboardActivity.class));
                                } else if ("teacher".equals(role)) {
                                    startActivity(new Intent(Login_Activity.this, TeacherDashboardActivity.class));
                                }
                                finish(); // Close login activity
                            }
                        }
                    });
                } else {
                    // Handle login failure
                }
            }
        });
    }

    private boolean validateData(String email, String password) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailedittxt.setError("Invalid email");
            return false;
        }
        if (password.length() < 6) {
            passedittxt.setError("Password length is invalid");
            return false;
        }
        return true;
    }
}
