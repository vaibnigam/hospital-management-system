package com.hospital.model;

public class Admin extends Person {
    private AdminAccessLevel accessLevel;

    public Admin(String name, String contactNumber, int age, AdminAccessLevel accessLevel) {
        super(name, contactNumber, age);
        this.accessLevel = accessLevel;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    public AdminAccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AdminAccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return super.toString() + ", Admin [accessLevel=" + accessLevel + "]";
    }
}