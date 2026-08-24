package com.hospital.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hospital.model.Patient;

public class PatientRepository {
    private Map<String, Patient> patients = new HashMap<>();

    public void addPatient(Patient patient) {
    	patients.put(patient.getId(), patient);
    }

    public Patient findById(String id) {
        return patients.get(id);
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<Patient>(patients.values());
    }
}