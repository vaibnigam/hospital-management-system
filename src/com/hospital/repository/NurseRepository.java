package com.hospital.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hospital.model.Nurse;

public class NurseRepository {
    private Map<String, Nurse> nurses = new HashMap<>();

    public void addNurse(Nurse nurse) {
        nurses.put(nurse.getId(), nurse);
    }

    public Nurse findById(String id) {
        return nurses.get(id);
    }

    public List<Nurse> getAllNurses() {
        return new ArrayList<Nurse>(nurses.values());
    }
}