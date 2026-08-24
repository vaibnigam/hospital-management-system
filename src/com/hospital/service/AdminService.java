package com.hospital.service;

import java.util.List;

import com.hospital.model.Admin;
import com.hospital.model.AdminAccessLevel;
import com.hospital.repository.AdminRepository;

public class AdminService {

	private AdminRepository adminRepository;

	private int nextAdminId = 1;

	public AdminService() {

		this.adminRepository = new AdminRepository();

	}

	public Admin addAdmin(String name, String contactNumber, int age, AdminAccessLevel accessLevel) {

		if (name == null || name.trim().isEmpty()) {
			return null;
		}

		if (age <= 0 || age >= 120) {
			return null;
		}

		if (contactNumber == null || !contactNumber.matches("[6-9][0-9]{9}")) {
			return null;
		}

		if (accessLevel == null) {
			return null;
		}

		String adminId = "AD" + nextAdminId;

		nextAdminId++;

		Admin admin = new Admin(name, contactNumber, age, accessLevel);

		admin.setId(adminId);

		adminRepository.addAdmin(admin);

		return admin;
	}

	public Admin searchAdminById(String id) {

		return adminRepository.findById(id);

	}

	public List<Admin> getAllAdmins() {

		return adminRepository.getAllAdmins();

	}
}