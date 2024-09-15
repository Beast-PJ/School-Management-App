package com.beast.schoolmanagementapp;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<Student> studentList;
    private SparseBooleanArray attendanceStatus;

    public AttendanceAdapter(List<Student> studentList) {
        this.studentList = studentList;
        attendanceStatus = new SparseBooleanArray(studentList.size()); // Track attendance status
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_attendance, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        Student student = studentList.get(position);

        // Bind student data
        holder.rollNumberTextView.setText(String.valueOf(student.getRollNumber()));
        holder.studentNameTextView.setText(student.getFirstName() + " " + student.getLastName());

        // Set checkbox listener to track attendance status
        holder.attendanceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            attendanceStatus.put(holder.getAdapterPosition(), isChecked); // Update attendance status
        });

        // Set the animation for each item
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.sildetoright));
    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public SparseBooleanArray getAttendanceStatus() {
        return attendanceStatus;
    }

    public static class AttendanceViewHolder extends RecyclerView.ViewHolder {

        TextView rollNumberTextView, studentNameTextView;
        CheckBox attendanceCheckBox;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            rollNumberTextView = itemView.findViewById(R.id.roll_number);
            studentNameTextView = itemView.findViewById(R.id.student_name);
            attendanceCheckBox = itemView.findViewById(R.id.attendanceCheckBox);
        }
    }
}
