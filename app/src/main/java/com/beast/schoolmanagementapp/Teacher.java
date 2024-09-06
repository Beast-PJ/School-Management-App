package com.beast.schoolmanagementapp;

public class Teacher {
    private String name;
    private String subject;
    private String email;

    public Teacher(String name, String subject, String email) {
        this.name = name;
        this.subject = subject;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmail() {
        return email;
    }
}
