package com.hospital.model;

public class Doctor extends Person {
    private String specialization;

    public Doctor(String name, String contactNumber, int age, String specialization) {
        super(name, contactNumber, age);
        this.specialization = specialization;
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return super.toString() + ", Doctor [specialization=" + specialization + "]";
    }
}