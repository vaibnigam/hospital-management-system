package com.hospital.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hospital.model.Admin;

public class AdminRepository {
    private Map<String, Admin> admins = new HashMap<>();

    public void addAdmin(Admin admin) {
        admins.put(admin.getId(), admin);
    }

    public Admin findById(String id) {
        return admins.get(id);
    }

    public List<Admin> getAllAdmins() {
        return new ArrayList<Admin>(admins.values());
    }
}