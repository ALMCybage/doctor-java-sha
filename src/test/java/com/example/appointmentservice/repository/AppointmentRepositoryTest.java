package com.example.appointmentservice.repository;

import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.model.Doctor;
import com.example.appointmentservice.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppointmentRepositoryTest {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    void testSaveAndFind() {
        Doctor doc = new Doctor();
        doc.setName("Dr. Repo");
        doc.setSpecialization("Test");
        doc.setEmail("repo@example.com");
        doc.setPhone("12345");
        doctorRepository.save(doc);

        Patient patient = new Patient();
        patient.setName("Test Patient");
        patient.setEmail("patient@example.com");
        patient.setPhone("67890");
        Patient savedPatient = patientRepository.save(patient);

        Appointment appt = new Appointment();
        appt.setDoctor(doc);
        appt.setPatient(savedPatient);
        appt.setAppointmentTime(LocalDateTime.now());
        appt.setStatus("BOOKED");
        Appointment saved = appointmentRepository.save(appt);
        assertNotNull(saved.getId());
        List<Appointment> byDoctor = appointmentRepository.findByDoctorId(doc.getId());
        assertFalse(byDoctor.isEmpty());
    }
}
