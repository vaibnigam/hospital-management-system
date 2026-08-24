package com.hospital.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient extends Person {
	private String diseaseInfo;
	private String assignedDoctorId;
	private String bloodGroup;
	private LocalDateTime admissionDateAndTime;

	public Patient(String name, String contactNumber, int age, String diseaseInfo, String assignedDoctorId,
			String bloodGroup) {
		super(name, contactNumber, age);
		this.diseaseInfo = diseaseInfo;
		this.assignedDoctorId = assignedDoctorId;
		this.bloodGroup = bloodGroup;
		this.admissionDateAndTime = LocalDateTime.now();
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

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public LocalDateTime getAdmissionDate() {
		return admissionDateAndTime;
	}

	public void setAdmissionDate(LocalDateTime admissionDate) {
		this.admissionDateAndTime = admissionDate;
	}

	@Override
	public String toString() {
		return super.toString() + ", Patient [diseaseInfo=" + diseaseInfo + ", assignedDoctorId=" + assignedDoctorId
				+ ", bloodGroup=" + bloodGroup + ", admissionDateAndTime=" + admissionDateAndTime + "]";
	}
}