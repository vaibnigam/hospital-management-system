package com.hospital.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.hospital.model.AdminAccessLevel;
import com.hospital.model.ShiftType;
import com.hospital.service.AdminService;
import com.hospital.service.DoctorService;
import com.hospital.service.NurseService;
import com.hospital.service.PatientService;

public class DataLoader {

    public void loadDoctorsFromFile(String filePath, DoctorService doctorService) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            int loadedCount = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 6) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String name = parts[0].trim();
                String contactNumber = parts[1].trim();
                int age = Integer.parseInt(parts[2].trim());
                String specialization = parts[3].trim();
                double consultationFee = Double.parseDouble(parts[4].trim());
                int experienceYears = Integer.parseInt(parts[5].trim());

                doctorService.addDoctor(name, contactNumber, age, specialization, consultationFee, experienceYears);
                loadedCount++;
            }

            fileScanner.close();
            System.out.println(loadedCount + " doctors loaded successfully from file.");

        } catch (FileNotFoundException e) {
            System.out.println("Doctors data file not found. Starting with no doctors.");
        }
    }

    public void loadPatientsFromFile(String filePath, PatientService patientService) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            int loadedCount = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 5) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String name = parts[0].trim();
                String contactNumber = parts[1].trim();
                int age = Integer.parseInt(parts[2].trim());
                String diseaseInfo = parts[3].trim();
                String bloodGroup = parts[4].trim();

                patientService.addPatient(name, contactNumber, age, diseaseInfo, bloodGroup);
                loadedCount++;
            }

            fileScanner.close();
            System.out.println(loadedCount + " patients loaded successfully from file.");

        } catch (FileNotFoundException e) {
            System.out.println("Patients data file not found. Starting with no patients.");
        }
    }

    public void loadNursesFromFile(String filePath, NurseService nurseService) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            int loadedCount = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 5) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String name = parts[0].trim();
                String contactNumber = parts[1].trim();
                int age = Integer.parseInt(parts[2].trim());
                String department = parts[3].trim();
                ShiftType shiftTiming = ShiftType.valueOf(parts[4].trim().toUpperCase());

                nurseService.addNurse(name, contactNumber, age, department, shiftTiming);
                loadedCount++;
            }

            fileScanner.close();
            System.out.println(loadedCount + " nurses loaded successfully from file.");

        } catch (FileNotFoundException e) {
            System.out.println("Nurses data file not found. Starting with no nurses.");
        }
    }

    public void loadAdminsFromFile(String filePath, AdminService adminService) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            int loadedCount = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 4) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String name = parts[0].trim();
                String contactNumber = parts[1].trim();
                int age = Integer.parseInt(parts[2].trim());
                AdminAccessLevel accessLevel = AdminAccessLevel.valueOf(parts[3].trim().toUpperCase());

                adminService.addAdmin(name, contactNumber, age, accessLevel);
                loadedCount++;
            }

            fileScanner.close();
            System.out.println(loadedCount + " admins loaded successfully from file.");

        } catch (FileNotFoundException e) {
            System.out.println("Admins data file not found. Starting with no admins.");
        }
    }
}