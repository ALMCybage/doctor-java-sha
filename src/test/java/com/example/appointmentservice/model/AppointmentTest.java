package com.example.appointmentservice.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    @Test
    void testGettersAndSetters() {
        Appointment appt = new Appointment();
        appt.setId(1L);
        appt.setDoctor(new Doctor());
        Patient patient = new Patient();
        patient.setId(101L);
        appt.setPatient(patient);
        appt.setAppointmentTime(LocalDateTime.of(2026,2,15,10,0));
        appt.setStatus("BOOKED");
        assertEquals(1L, appt.getId());
        assertNotNull(appt.getDoctor());
        assertEquals(101L, appt.getPatient().getId());
        assertEquals(LocalDateTime.of(2026,2,15,10,0), appt.getAppointmentTime());
        assertEquals("BOOKED", appt.getStatus());
    }
}
