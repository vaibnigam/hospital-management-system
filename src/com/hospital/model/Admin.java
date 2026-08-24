package com.hospital.model;

public class Admin extends Person {
    private String accessLevel;

    public Admin(String name, String contactNumber, int age, String accessLevel) {
        super(name, contactNumber, age);
        this.accessLevel = accessLevel;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return super.toString() + ", Admin [accessLevel=" + accessLevel + "]";
    }
}