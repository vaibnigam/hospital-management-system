package com.hospital.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hospital.model.Appointment;
import com.hospital.model.AppointmentStatus;
import com.hospital.repository.AppointmentRepository;

public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    private PatientService patientService;

    private DoctorService doctorService;

    private int nextAppointmentId = 1;

    public AppointmentService(PatientService patientService,
                              DoctorService doctorService) {

        this.appointmentRepository = new AppointmentRepository();

        this.patientService = patientService;

        this.doctorService = doctorService;
    }

    public Appointment scheduleAppointment(String patientId,
                                            String doctorId,
                                            LocalDateTime appointmentDate) {

        if (patientService.searchPatientById(patientId) == null) {
            return null;
        }

        if (doctorService.searchDoctorById(doctorId) == null) {
            return null;
        }

        if (appointmentDate == null ||
            !appointmentDate.isAfter(LocalDateTime.now())) {
            return null;
        }

        String appointmentId = "A" + nextAppointmentId;

        nextAppointmentId++;

        Appointment appointment = new Appointment(
                patientId,
                doctorId,
                appointmentDate
        );

        appointment.setAppointmentId(appointmentId);

        appointmentRepository.addAppointment(appointment);

        return appointment;
    }

    public boolean completeAppointment(String appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId);

        if (appointment == null) {
            return false;
        }

        if (appointment.getStatus() == AppointmentStatus.COMPLETED ||
            appointment.getStatus() == AppointmentStatus.CANCELLED) {
            return false;
        }

        appointment.setStatus(AppointmentStatus.COMPLETED);

        return true;
    }

    public boolean cancelAppointment(String appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId);

        if (appointment == null) {
            return false;
        }

        if (appointment.getStatus() == AppointmentStatus.COMPLETED ||
            appointment.getStatus() == AppointmentStatus.CANCELLED) {
            return false;
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);

        return true;
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {

        return appointmentRepository.findByPatientId(patientId);

    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {

        return appointmentRepository.findByDoctorId(doctorId);

    }
}