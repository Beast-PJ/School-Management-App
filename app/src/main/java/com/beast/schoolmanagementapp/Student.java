package com.beast.schoolmanagementapp;

public class Student {
    private int rollNumber;
    private String firstName;
    private String middleName;
    private String lastName;

    // Constructor
    public Student(int rollNumber, String firstName, String middleName, String lastName) {
        this.rollNumber = rollNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // Getters
    public int getRollNumber() {
        return rollNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    // Setters
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
