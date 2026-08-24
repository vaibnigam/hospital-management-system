package com.hospital.model;

public class Patient extends Person {
	private String diseaseInfo;
	private String assignedDoctorId;

	public Patient(String name, String contactNumber, int age, String diseaseInfo, String assignedDoctorId) {
		super(name, contactNumber, age);
		this.diseaseInfo = diseaseInfo;
		this.assignedDoctorId = assignedDoctorId;
	}

	@Override
	public String getRole() {
		return "Patient";
	}

	public String getDiseaseInfo() {
		return diseaseInfo;
	}

	public void setDiseaseInfo(String diseaseInfo) {
		this.diseaseInfo = diseaseInfo;
	}

	public String getAssignedDoctorId() {
		return assignedDoctorId;
	}

	public void setAssignedDoctorId(String assignedDoctorId) {
		this.assignedDoctorId = assignedDoctorId;
	}

	@Override
	public String toString() {
		return super.toString() + ", Patient [diseaseInfo=" + diseaseInfo + ", assignedDoctorId=" + assignedDoctorId
				+ "]";
	}

}