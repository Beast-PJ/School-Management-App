package com.beast.schoolmanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<Student> studentList;

    public AttendanceAdapter(List<Student> studentList) {
        this.studentList = studentList;
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
        holder.studentName.setText(student.getName());
        holder.attendanceCheckBox.setChecked(student.isPresent());

        holder.attendanceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> student.setPresent(isChecked));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public List<Student> getAttendanceList() {
        return studentList;
    }

    static class AttendanceViewHolder extends RecyclerView.ViewHolder {

        TextView studentName;
        CheckBox attendanceCheckBox;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.student_name);
            attendanceCheckBox = itemView.findViewById(R.id.attendance_checkbox);
        }
    }
}
