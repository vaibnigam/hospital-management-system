package com.hospital.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hospital.model.Appointment;

public class AppointmentRepository {

    private Map<String, Appointment> appointments = new HashMap<>();

    public void addAppointment(Appointment appointment) {

        appointments.put(appointment.getAppointmentId(), appointment);

    }

    public Appointment findById(String id) {

        return appointments.get(id);

    }

    public List<Appointment> getAllAppointments() {

        return new ArrayList<>(appointments.values());

    }

    public List<Appointment> findByPatientId(String patientId) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment appointment : appointments.values()) {

            if (appointment.getPatientId().equals(patientId)) {
                result.add(appointment);
            }

        }

        return result;
    }

    public List<Appointment> findByDoctorId(String doctorId) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment appointment : appointments.values()) {

            if (appointment.getDoctorId().equals(doctorId)) {
                result.add(appointment);
            }

        }

        return result;
    }

}