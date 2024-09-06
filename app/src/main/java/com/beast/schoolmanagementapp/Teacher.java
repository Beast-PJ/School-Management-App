package com.beast.schoolmanagementapp;

public class Teacher {
    private String name;
    private String phoneNumber;
    private int avatarResId;

    public Teacher(String name, String phoneNumber, int avatarResId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatarResId = avatarResId; // Resource ID for the avatar image
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public void setAvatarResId(int avatarResId) {
        this.avatarResId = avatarResId;
    }
}
