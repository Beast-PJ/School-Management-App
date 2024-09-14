package com.beast.schoolmanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beast.schoolmanagementapp.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    // Constructor
    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    // ViewHolder class to hold the views for each item
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView rollNumberTextView, studentNameTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            rollNumberTextView = itemView.findViewById(R.id.txt_student_roll);
            studentNameTextView = itemView.findViewById(R.id.txt_student_name); // Make sure you have a TextView with this ID
        }
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_attendance, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        // Get the current student from the list
        Student student = studentList.get(position);

        // Set the roll number
        holder.rollNumberTextView.setText(String.valueOf(student.getRollNumber()));

        // Concatenate the student's full name (first, middle, last)
        String fullName = student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName();
        holder.studentNameTextView.setText(fullName);
    }

    @Override
    public int getItemCount() {
        return studentList.size(); // Return the total number of students
    }
}
