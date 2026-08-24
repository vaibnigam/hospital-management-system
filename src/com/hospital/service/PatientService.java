package com.hospital.service;

import java.util.List;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;

public class PatientService {

    private PatientRepository patientRepository;
    private DoctorService doctorService;
    private int nextPatientId = 1;

    public PatientService(DoctorService doctorService) {
        this.patientRepository = new PatientRepository();
        this.doctorService = doctorService;
    }

    public Patient addPatient(String name, String contactNumber, int age,
                              String diseaseInfo, String bloodGroup) {

        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        if (age <= 0 || age >= 120) {
            return null;
        }
        if (contactNumber == null || !contactNumber.matches("[6-9][0-9]{9}")) {
            return null;
        }

        String patientId = "P" + nextPatientId;
        nextPatientId++;

        Patient patient = new Patient(name, contactNumber, age, diseaseInfo, "Not Assigned", bloodGroup);
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

    public boolean reassignDoctor(String patientId, String newDoctorId) {
        Patient patient = patientRepository.findById(patientId);
        if (patient == null) {
            return false;
        }
        if (doctorService.searchDoctorById(newDoctorId) == null) {
            return false;
        }
        patient.setAssignedDoctorId(newDoctorId);
        return true;
    }
}