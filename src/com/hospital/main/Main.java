package com.hospital.main;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.hospital.loader.DataLoader;
import com.hospital.model.Admin;
import com.hospital.model.AdminAccessLevel;
import com.hospital.model.Appointment;
import com.hospital.model.Billing;
import com.hospital.model.Doctor;
import com.hospital.model.Nurse;
import com.hospital.model.Patient;
import com.hospital.model.ShiftType;
import com.hospital.service.AdminService;
import com.hospital.service.AppointmentService;
import com.hospital.service.BillingService;
import com.hospital.service.DoctorService;
import com.hospital.service.NurseService;
import com.hospital.service.PatientService;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		DoctorService doctorService = new DoctorService();
		PatientService patientService = new PatientService(doctorService);
		NurseService nurseService = new NurseService();
		AdminService adminService = new AdminService();
		AppointmentService appointmentService = new AppointmentService(patientService, doctorService);
		BillingService billingService = new BillingService(patientService);

		boolean running = true;

		DataLoader dataLoader = new DataLoader();
		dataLoader.loadDoctorsFromFile("data/doctors.txt", doctorService);
		dataLoader.loadPatientsFromFile("data/patients.txt", patientService);
		dataLoader.loadNursesFromFile("data/nurses.txt", nurseService);
		dataLoader.loadAdminsFromFile("data/admins.txt", adminService);

		while (running) {

			System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
			System.out.println("1. Add Patient");
			System.out.println("2. Add Doctor");
			System.out.println("3. Add Nurse");
			System.out.println("4. Add Admin");
			System.out.println("5. View All Patients");
			System.out.println("6. View All Doctors");
			System.out.println("7. View All Nurses");
			System.out.println("8. View All Admins");
			System.out.println("9. Search Patient by ID");
			System.out.println("10. Search Doctor by ID");
			System.out.println("11. Search Nurse by ID");
			System.out.println("12. Search Admin by ID");
			System.out.println("13. Search Patient by Name");
			System.out.println("14. Schedule Appointment");
			System.out.println("15. Complete Appointment");
			System.out.println("16. Cancel Appointment");
			System.out.println("17. View Appointments by Patient");
			System.out.println("18. View Appointments by Doctor");
			System.out.println("19. View All Appointments");
			System.out.println("20. Reassign Doctor to Patient");
			System.out.println("21. Generate Bill");
			System.out.println("22. Mark Bill as Paid");
			System.out.println("23. View Billing History");
			System.out.println("24. Exit");
			System.out.print("Enter your choice: ");

			int choice;
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine();
				continue;
			}

			switch (choice) {

			case 1:
				System.out.print("Enter Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter Contact Number: ");
				String contactNumber = scanner.nextLine();
				System.out.print("Enter Age: ");
				int age = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Disease Info: ");
				String diseaseInfo = scanner.nextLine();
				System.out.print("Enter Blood Group: ");
				String bloodGroup = scanner.nextLine();

				Patient newPatient = patientService.addPatient(name, contactNumber, age, diseaseInfo, bloodGroup);
				System.out.println(newPatient != null ? "Patient added! ID: " + newPatient.getId()
						: "Failed to add patient. Check inputs.");
				break;

			case 2:
				System.out.print("Enter Name: ");
				String doctorName = scanner.nextLine();
				System.out.print("Enter Contact Number: ");
				String doctorContact = scanner.nextLine();
				System.out.print("Enter Age: ");
				int doctorAge = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Specialization: ");
				String specialization = scanner.nextLine();
				System.out.print("Enter Consultation Fee: ");
				double consultationFee = scanner.nextDouble();
				System.out.print("Enter Experience Years: ");
				int experienceYears = scanner.nextInt();
				scanner.nextLine();

				Doctor newDoctor = doctorService.addDoctor(doctorName, doctorContact, doctorAge, specialization,
						consultationFee, experienceYears);
				System.out.println(newDoctor != null ? "Doctor added! ID: " + newDoctor.getId()
						: "Failed to add doctor. Check inputs.");
				break;

			case 3:
				System.out.print("Enter Name: ");
				String nurseName = scanner.nextLine();
				System.out.print("Enter Contact Number: ");
				String nurseContact = scanner.nextLine();
				System.out.print("Enter Age: ");
				int nurseAge = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Department: ");
				String department = scanner.nextLine();

				System.out.println("Available Shift Types:");
				for (ShiftType shift : ShiftType.values()) {
					System.out.println(shift);
				}
				System.out.print("Enter Shift Type: ");
				String shiftInput = scanner.nextLine();

				try {
					ShiftType shiftTiming = ShiftType.valueOf(shiftInput.toUpperCase());
					Nurse newNurse = nurseService.addNurse(nurseName, nurseContact, nurseAge, department, shiftTiming);
					System.out.println(newNurse != null ? "Nurse added! ID: " + newNurse.getId()
							: "Failed to add nurse. Check inputs.");
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid shift type.");
				}
				break;

			case 4:
				System.out.print("Enter Name: ");
				String adminName = scanner.nextLine();
				System.out.print("Enter Contact Number: ");
				String adminContact = scanner.nextLine();
				System.out.print("Enter Age: ");
				int adminAge = scanner.nextInt();
				scanner.nextLine();

				System.out.println("Available Access Levels:");
				for (AdminAccessLevel level : AdminAccessLevel.values()) {
					System.out.println(level);
				}
				System.out.print("Enter Access Level: ");
				String accessInput = scanner.nextLine();

				try {
					AdminAccessLevel accessLevel = AdminAccessLevel.valueOf(accessInput.toUpperCase());
					Admin newAdmin = adminService.addAdmin(adminName, adminContact, adminAge, accessLevel);
					System.out.println(newAdmin != null ? "Admin added! ID: " + newAdmin.getId()
							: "Failed to add admin. Check inputs.");
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid access level.");
				}
				break;

			case 5:
				List<Patient> patients = patientService.getAllPatients();
				if (patients.isEmpty()) {
					System.out.println("No patients found.");
				} else {
					System.out.println("\n===== ALL PATIENTS =====");
					for (Patient patient : patients) {
						System.out.println(patient);
					}
				}
				break;

			case 6:
				List<Doctor> doctors = doctorService.getAllDoctors();
				if (doctors.isEmpty()) {
					System.out.println("No doctors found.");
				} else {
					System.out.println("\n===== ALL DOCTORS =====");
					for (Doctor doctor : doctors) {
						System.out.println(doctor);
					}
				}
				break;

			case 7:
				List<Nurse> nurses = nurseService.getAllNurses();
				if (nurses.isEmpty()) {
					System.out.println("No nurses found.");
				} else {
					System.out.println("\n===== ALL NURSES =====");
					for (Nurse nurse : nurses) {
						System.out.println(nurse);
					}
				}
				break;

			case 8:
				List<Admin> admins = adminService.getAllAdmins();
				if (admins.isEmpty()) {
					System.out.println("No admins found.");
				} else {
					System.out.println("\n===== ALL ADMINS =====");
					for (Admin admin : admins) {
						System.out.println(admin);
					}
				}
				break;

			case 9:
				System.out.print("Enter Patient ID: ");
				String searchPatientId = scanner.nextLine();
				Patient foundPatient = patientService.searchPatientById(searchPatientId);
				System.out.println(foundPatient != null ? foundPatient : "Patient not found.");
				break;

			case 10:
				System.out.print("Enter Doctor ID: ");
				String searchDoctorId = scanner.nextLine();
				Doctor foundDoctor = doctorService.searchDoctorById(searchDoctorId);
				System.out.println(foundDoctor != null ? foundDoctor : "Doctor not found.");
				break;

			case 11:
				System.out.print("Enter Nurse ID: ");
				String searchNurseId = scanner.nextLine();
				Nurse foundNurse = nurseService.searchNurseById(searchNurseId);
				System.out.println(foundNurse != null ? foundNurse : "Nurse not found.");
				break;

			case 12:
				System.out.print("Enter Admin ID: ");
				String searchAdminId = scanner.nextLine();
				Admin foundAdmin = adminService.searchAdminById(searchAdminId);
				System.out.println(foundAdmin != null ? foundAdmin : "Admin not found.");
				break;

			case 13:
				System.out.print("Enter Name to search: ");
				String searchName = scanner.nextLine();
				List<Patient> matchingPatients = patientService.searchPatientByName(searchName);
				if (matchingPatients.isEmpty()) {
					System.out.println("No patients found.");
				} else {
					for (Patient p : matchingPatients) {
						System.out.println(p);
					}
				}
				break;

			case 14:
				System.out.print("Enter Patient ID: ");
				String patientId = scanner.nextLine();
				System.out.print("Enter Doctor ID: ");
				String doctorId = scanner.nextLine();
				System.out.print("Enter Appointment Date (yyyy-MM-ddTHH:mm, e.g. 2026-09-01T10:30): ");
				String dateInput = scanner.nextLine();

				try {
					LocalDateTime appointmentDate = LocalDateTime.parse(dateInput);
					Appointment appointment = appointmentService.scheduleAppointment(patientId, doctorId,
							appointmentDate);
					System.out.println(
							appointment != null ? "Appointment scheduled! ID: " + appointment.getAppointmentId()
									: "Scheduling failed.");
				} catch (Exception e) {
					System.out.println("Invalid date format.");
				}
				break;

			case 15:
				System.out.print("Enter Appointment ID: ");
				String completeAppointmentId = scanner.nextLine();
				boolean completed = appointmentService.completeAppointment(completeAppointmentId);
				System.out
						.println(completed ? "Appointment completed successfully." : "Failed to complete appointment.");
				break;

			case 16:
				System.out.print("Enter Appointment ID: ");
				String cancelAppointmentId = scanner.nextLine();
				boolean cancelled = appointmentService.cancelAppointment(cancelAppointmentId);
				System.out.println(cancelled ? "Appointment cancelled successfully." : "Failed to cancel appointment.");
				break;

			case 17:
				System.out.print("Enter Patient ID: ");
				String appointmentPatientId = scanner.nextLine();
				List<Appointment> patientAppointments = appointmentService
						.getAppointmentsByPatient(appointmentPatientId);
				if (patientAppointments.isEmpty()) {
					System.out.println("No appointments found.");
				} else {
					System.out.println("\n===== PATIENT APPOINTMENTS =====");
					for (Appointment appt : patientAppointments) {
						System.out.println(appt);
					}
				}
				break;

			case 18:
				System.out.print("Enter Doctor ID: ");
				String appointmentDoctorId = scanner.nextLine();
				List<Appointment> doctorAppointments = appointmentService.getAppointmentsByDoctor(appointmentDoctorId);
				if (doctorAppointments.isEmpty()) {
					System.out.println("No appointments found.");
				} else {
					System.out.println("\n===== DOCTOR APPOINTMENTS =====");
					for (Appointment appt : doctorAppointments) {
						System.out.println(appt);
					}
				}
				break;

			case 19:
				List<Appointment> allAppointments = appointmentService.getAllAppointments();
				if (allAppointments.isEmpty()) {
					System.out.println("No appointments found.");
				} else {
					System.out.println("\n===== ALL APPOINTMENTS =====");
					for (Appointment appt : allAppointments) {
						System.out.println(appt);
					}
				}
				break;

			case 20:
				System.out.print("Enter Patient ID: ");
				String reassignPatientId = scanner.nextLine();
				System.out.print("Enter New Doctor ID: ");
				String newDoctorId = scanner.nextLine();
				boolean reassigned = patientService.reassignDoctor(reassignPatientId, newDoctorId);
				System.out.println(reassigned ? "Doctor reassigned successfully."
						: "Reassignment failed. Check patient/doctor IDs.");
				break;

			case 21:
				System.out.print("Enter Patient ID: ");
				String billingPatientId = scanner.nextLine();
				System.out.print("Enter Bill Amount: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();

				Billing billing = billingService.generateBill(billingPatientId, amount);
				System.out.println(billing != null ? "Bill generated! Bill ID: " + billing.getBillId()
						: "Failed to generate bill.");
				break;

			case 22:
				System.out.print("Enter Bill ID: ");
				String billId = scanner.nextLine();
				boolean paid = billingService.markAsPaid(billId);
				System.out.println(paid ? "Bill marked as paid successfully." : "Failed to mark bill as paid.");
				break;

			case 23:
				System.out.print("Enter Patient ID: ");
				String historyPatientId = scanner.nextLine();
				List<Billing> billingHistory = billingService.getBillingHistory(historyPatientId);
				if (billingHistory.isEmpty()) {
					System.out.println("No billing history found.");
				} else {
					System.out.println("\n===== BILLING HISTORY =====");
					for (Billing bill : billingHistory) {
						System.out.println(bill);
					}
				}
				break;

			case 24:
				System.out.println("Thank you for using Hospital Management System!");
				running = false;
				break;

			default:
				System.out.println("Invalid choice. Please select 1-24.");
			}
		}

		scanner.close();
	}
}