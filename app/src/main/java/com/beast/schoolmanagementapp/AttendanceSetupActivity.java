// AttendanceSetupActivity.java
package com.beast.schoolmanagementapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AttendanceSetupActivity extends AppCompatActivity {

    private Spinner spinnerStandard, spinnerClass, spinnerTimePeriod;
    private TextView selectedDate;
    private Button btnProceed;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_setup);

        spinnerStandard = findViewById(R.id.spinner_standard);
        spinnerClass = findViewById(R.id.spinner_class);
        spinnerTimePeriod = findViewById(R.id.spinner_time_period);
        selectedDate = findViewById(R.id.txt_selected_date);
        btnProceed = findViewById(R.id.btn_proceed);

        // Set up Spinners
        ArrayAdapter<CharSequence> adapterStandard = ArrayAdapter.createFromResource(this, R.array.standard_array, android.R.layout.simple_spinner_item);
        adapterStandard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStandard.setAdapter(adapterStandard);

        ArrayAdapter<CharSequence> adapterClass = ArrayAdapter.createFromResource(this, R.array.class_array, android.R.layout.simple_spinner_item);
        adapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(adapterClass);

        ArrayAdapter<CharSequence> adapterTimePeriod = ArrayAdapter.createFromResource(this, R.array.time_period_array, android.R.layout.simple_spinner_item);
        adapterTimePeriod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimePeriod.setAdapter(adapterTimePeriod);

        // Set up DatePickerDialog
        calendar = Calendar.getInstance();
        selectedDate.setOnClickListener(v -> showDatePickerDialog());

        // Button click to proceed
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedStandard = spinnerStandard.getSelectedItem().toString();
                String selectedClass = spinnerClass.getSelectedItem().toString();
                String selectedTimePeriod = spinnerTimePeriod.getSelectedItem().toString();
                String date = selectedDate.getText().toString();

                if (selectedStandard.isEmpty() || selectedClass.isEmpty() || selectedTimePeriod.isEmpty() || date.equals("Select Date")) {
                    Toast.makeText(AttendanceSetupActivity.this, "Please select all options", Toast.LENGTH_SHORT).show();
                } else {
                    // Pass data to next activity
                    Intent intent = new Intent(AttendanceSetupActivity.this, AttendanceActivity.class);
                    intent.putExtra("standard", selectedStandard);
                    intent.putExtra("class", selectedClass);
                    intent.putExtra("timePeriod", selectedTimePeriod);
                    intent.putExtra("date", date);
                    startActivity(intent);
                }
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> selectedDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
