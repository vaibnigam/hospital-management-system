package com.hospital.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hospital.model.Doctor;

public class DoctorRepository {
	private Map<String, Doctor> doctors = new HashMap<>();

	public void addDoctor(Doctor doctor) {
		doctors.put(doctor.getId(), doctor);
	}

	public Doctor findById(String id) {
		return doctors.get(id);
	}

	public List<Doctor> getAllDoctors() {
		return new ArrayList<Doctor>(doctors.values());
	}
}
