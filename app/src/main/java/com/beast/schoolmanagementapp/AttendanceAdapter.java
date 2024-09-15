package com.beast.schoolmanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Map;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<Student> studentList;
    private Map<Integer, Boolean> attendanceStatus;

    public AttendanceAdapter(List<Student> studentList, Map<Integer, Boolean> attendanceStatus) {
        this.studentList = studentList;
        this.attendanceStatus = attendanceStatus;
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
        holder.tvStudentName.setText(student.getName());
        holder.tvRollNo.setText("Roll No: " + student.getRollNo());

        // Set the checkbox state based on the attendance status map
        holder.attendanceCheckBox.setOnCheckedChangeListener(null); // Prevent triggering listener when resetting state
        holder.attendanceCheckBox.setChecked(attendanceStatus.getOrDefault(position, false));

        // Set the listener to update the attendance status
        holder.attendanceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            attendanceStatus.put(position, isChecked);
            // Update stats in the parent activity
            if (buttonView.getContext() instanceof AttendanceActivity) {
                ((AttendanceActivity) buttonView.getContext()).updateAttendanceStats();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class AttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName, tvRollNo;
        CheckBox attendanceCheckBox;

        AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvRollNo = itemView.findViewById(R.id.tvRollNo);
            attendanceCheckBox = itemView.findViewById(R.id.attendanceCheckBox);
        }
    }
}
