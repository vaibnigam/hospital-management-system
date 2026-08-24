package com.hospital.service;

import java.util.List;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;

public class PatientService {

    private PatientRepository patientRepository;

    private int nextPatientId = 1;

    public PatientService() {

        this.patientRepository = new PatientRepository();

    }

    public Patient addPatient(String name, String contactNumber, int age,
                              String diseaseInfo, String assignedDoctorId,
                              String bloodGroup) {

        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        if (age <= 0 || age >= 120) {
            return null;
        }

        if (contactNumber == null ||
            !contactNumber.matches("[6-9][0-9]{9}")) {
            return null;
        }

        String patientId = "P" + nextPatientId;

        nextPatientId++;

        Patient patient = new Patient(
                name,
                contactNumber,
                age,
                diseaseInfo,
                assignedDoctorId,
                bloodGroup
        );

        patient.setId(patientId);

        patientRepository.addPatient(patient);

        return patient;
    }

    public Patient searchPatientById(String id) {

        return patientRepository.findById(id);

    }

    public List<Patient> searchPatientByName(String name) {

        return patientRepository.findByName(name);

    }

    public List<Patient> getAllPatients() {

        return patientRepository.getAllPatients();

    }
}