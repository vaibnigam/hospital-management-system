package com.hospital.service;

import java.util.List;

import com.hospital.model.Doctor;
import com.hospital.repository.DoctorRepository;

public class DoctorService {

	private DoctorRepository doctorRepository;

	private int nextDoctorId = 1;

	public DoctorService() {

		this.doctorRepository = new DoctorRepository();

	}

	public Doctor addDoctor(String name, String contactNumber, int age, String specialization, double consultationFee,
			int experienceYears) {

		if (name == null || name.trim().isEmpty()) {
			return null;
		}

		if (age <= 0 || age >= 120) {
			return null;
		}

		if (contactNumber == null || !contactNumber.matches("[6-9][0-9]{9}")) {
			return null;
		}

		if (consultationFee <= 0) {
			return null;
		}

		if (experienceYears < 0) {
			return null;
		}

		String doctorId = "D" + nextDoctorId;

		nextDoctorId++;

		Doctor doctor = new Doctor(name, contactNumber, age, specialization, consultationFee, experienceYears);

		doctor.setId(doctorId);

		doctorRepository.addDoctor(doctor);

		return doctor;
	}

	public Doctor searchDoctorById(String id) {

		return doctorRepository.findById(id);

	}

	public List<Doctor> getAllDoctors() {

		return doctorRepository.getAllDoctors();

	}
}