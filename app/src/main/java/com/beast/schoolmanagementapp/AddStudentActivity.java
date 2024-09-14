package com.beast.schoolmanagementapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etRollNo, etFirstName, etMiddleName, etSurname, etStudentPhone;
    private Spinner spinnerStandard, spinnerClass;
    private Button btnAddStudent;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Students");

        // Initialize Views
        etRollNo = findViewById(R.id.etRollNo);
        etFirstName = findViewById(R.id.etFirstName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etSurname = findViewById(R.id.etSurname);
        etStudentPhone = findViewById(R.id.etStudentPhone);
        spinnerStandard = findViewById(R.id.spinnerStandard);
        spinnerClass = findViewById(R.id.spinnerClass);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        // Populate spinnerStandard
        ArrayAdapter<String> adapterStandard = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.standard_array)) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.spinner_item, parent, false);
                }
                TextView textView = convertView.findViewById(R.id.spinner_text);
                textView.setText(getItem(position));
                return convertView;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.spinner_item, parent, false);
                }
                TextView textView = convertView.findViewById(R.id.spinner_text);
                textView.setText(getItem(position));
                return convertView;
            }
        };
        spinnerStandard.setAdapter(adapterStandard);

// Similarly for class spinner
        ArrayAdapter<String> adapterClass = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.class_array)) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.spinner_item, parent, false);
                }
                TextView textView = convertView.findViewById(R.id.spinner_text);
                textView.setText(getItem(position));
                return convertView;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.spinner_item, parent, false);
                }
                TextView textView = convertView.findViewById(R.id.spinner_text);
                textView.setText(getItem(position));
                return convertView;
            }
        };
        spinnerClass.setAdapter(adapterClass);

        // Add Student Button Click Listener
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });
    }

    private void addStudent() {
        String rollNo = etRollNo.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        String middleName = etMiddleName.getText().toString().trim();
        String surname = etSurname.getText().toString().trim();
        String phone = etStudentPhone.getText().toString().trim();
        String standard = spinnerStandard.getSelectedItem().toString();
        String className = spinnerClass.getSelectedItem().toString();

        if (rollNo.isEmpty() || firstName.isEmpty() || surname.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a Student object
        String studentId = databaseReference.push().getKey();
        Map<String, Object> studentData = new HashMap<>();
        studentData.put("id", studentId);
        studentData.put("rollNo", rollNo);
        studentData.put("firstName", firstName);
        studentData.put("middleName", middleName);
        studentData.put("surname", surname);
        studentData.put("phone", phone);
        studentData.put("standard", standard);
        studentData.put("class", className);

        // Save data to Firebase
        if (studentId != null) {
            databaseReference.child(standard).child(className).child(studentId).setValue(studentData)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddStudentActivity.this, "Student added successfully", Toast.LENGTH_SHORT).show();
                            clearFields();
                        } else {
                            Toast.makeText(AddStudentActivity.this, "Failed to add student", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void clearFields() {
        etRollNo.setText("");
        etFirstName.setText("");
        etMiddleName.setText("");
        etSurname.setText("");
        etStudentPhone.setText("");
        spinnerStandard.setSelection(0);
        spinnerClass.setSelection(0);
    }

    private void animateView(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.05f, 1.0f, 1.05f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(150);
        scaleAnimation.setFillAfter(true);
        view.startAnimation(scaleAnimation);
    }
}
