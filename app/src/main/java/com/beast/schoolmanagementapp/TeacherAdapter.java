package com.beast.schoolmanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {

    private List<Teacher> teacherList;
    private OnTeacherClickListener listener;

    public interface OnTeacherClickListener {
        void onEditClick(Teacher teacher);
        void onDeleteClick(Teacher teacher);
    }

    public TeacherAdapter(List<Teacher> teacherList, OnTeacherClickListener listener) {
        this.teacherList = teacherList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_list_item, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher teacher = teacherList.get(position);
        holder.nameTextView.setText(teacher.getName());
        holder.subjectTextView.setText(teacher.getSubject());
        holder.emailTextView.setText(teacher.getEmail());

        // Set click listeners for buttons
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClick(teacher);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(teacher);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    static class TeacherViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView subjectTextView;
        TextView emailTextView;
        Button editButton;
        Button deleteButton;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.teacherNameTextView);
            subjectTextView = itemView.findViewById(R.id.teacherSubjectTextView);
            emailTextView = itemView.findViewById(R.id.teacherEmailTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
