package com.hospital.model;

public class Doctor extends Person {
	private String specialization;
	private double consultationFee;
	private int experienceYears;

	public Doctor(String name, String contactNumber, int age, String specialization, double consultationFee,
			int experienceYears) {
		super(name, contactNumber, age);
		this.specialization = specialization;
		this.consultationFee = consultationFee;
		this.experienceYears = experienceYears;
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

	public double getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(double consultationFee) {
		this.consultationFee = consultationFee;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	@Override
	public String toString() {
		return super.toString() + ", Doctor [specialization=" + specialization + ", consultationFee=" + consultationFee
				+ ", experienceYears=" + experienceYears + "]";
	}
}