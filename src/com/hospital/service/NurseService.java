package com.hospital.service;

import java.util.List;

import com.hospital.model.Nurse;
import com.hospital.model.ShiftType;
import com.hospital.repository.NurseRepository;

public class NurseService {

    private NurseRepository nurseRepository;

    private int nextNurseId = 1;

    public NurseService() {

        this.nurseRepository = new NurseRepository();

    }

    public Nurse addNurse(String name, String contactNumber, int age,
                          String department, ShiftType shiftTiming) {

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

        String nurseId = "N" + nextNurseId;

        nextNurseId++;

        Nurse nurse = new Nurse(
                name,
                contactNumber,
                age,
                department,
                shiftTiming
        );

        nurse.setId(nurseId);

        nurseRepository.addNurse(nurse);

        return nurse;
    }

    public Nurse searchNurseById(String id) {

        return nurseRepository.findById(id);

    }

    public List<Nurse> getAllNurses() {

        return nurseRepository.getAllNurses();

    }
}