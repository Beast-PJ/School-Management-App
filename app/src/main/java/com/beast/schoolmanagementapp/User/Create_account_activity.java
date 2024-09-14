package com.beast.schoolmanagementapp.User;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.beast.schoolmanagementapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Create_account_activity extends AppCompatActivity {

    EditText emailedittxt, passedittxt, passcontxt;
    Button createacc;
    ProgressBar progressBar;
    CardView cardView, cardView2;
    TextView loginbtntxt, textView1;
    LinearLayout linearLayout;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize Firebase Auth and Firestore
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // UI elements
        emailedittxt = findViewById(R.id.email_edit_text);
        passedittxt = findViewById(R.id.pass_edit_text);
        passcontxt = findViewById(R.id.pass_conform_edit_text);
        createacc = findViewById(R.id.create_acc_btn);
        progressBar = findViewById(R.id.progressbar);
        loginbtntxt = findViewById(R.id.login_txtview_btn);
        textView1 = findViewById(R.id.textview);
        cardView = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView21);
        linearLayout = findViewById(R.id.toplinearLayout1);

        Animation drop_down = AnimationUtils.loadAnimation(this, R.anim.bottom_down);
        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        cardView.startAnimation(drop_down);
        cardView2.startAnimation(fade_in);
        textView1.startAnimation(drop_down);
        linearLayout.startAnimation(drop_down);

        createacc.setOnClickListener(view -> CreateAcc());
        loginbtntxt.setOnClickListener(view -> finish());
    }

    void CreateAcc() {
        String email = emailedittxt.getText().toString();
        String pass = passedittxt.getText().toString();
        String conpass = passcontxt.getText().toString();

        boolean isValidated = validate_data(email, pass, conpass);
        if (!isValidated) {
            return;
        }

        createacc_fire(email, pass);
    }

    boolean validate_data(String email, String pass, String conpass) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailedittxt.setError("Invalid Email");
            return false;
        }
        if (passedittxt.length() < 6) {
            passedittxt.setError("Password length is Invalid");
            return false;
        }
        if (!pass.equals(conpass)) {
            passcontxt.setError("Invalid Confirm Password");
            return false;
        }
        return true;
    }

    void createacc_fire(String email, String pass) {
        change_in_progress(true);

        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(Create_account_activity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = firebaseAuth.getCurrentUser().getUid();
                    String role = pass.equals("p.jadhav") ? "admin" : "teacher";

                    // Save role in Firestore
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("email", email);
                    userData.put("role", role);

                    firestore.collection("users").document(userId).set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Create_account_activity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                                firebaseAuth.signOut();
                                finish();
                            } else {
                                Toast.makeText(Create_account_activity.this, "Failed to save user role", Toast.LENGTH_SHORT).show();
                            }
                            change_in_progress(false);
                        }
                    });
                } else {
                    Toast.makeText(Create_account_activity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    change_in_progress(false);
                }
            }
        });
    }

    void change_in_progress(Boolean inprogress) {
        if (inprogress) {
            progressBar.setVisibility(View.VISIBLE);
            createacc.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            createacc.setVisibility(View.VISIBLE);
        }
    }
}
