package com.hospital.model;

public class Nurse extends Person {
    private String department;

    public Nurse(String name, String contactNumber, int age, String department) {
        super(name, contactNumber, age);
        this.department = department;
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString() + ", Nurse [department=" + department + "]";
    }
}