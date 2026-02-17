package com.example.appointmentservice.repository;

import com.example.appointmentservice.model.Billing;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.model.Doctor;
import com.example.appointmentservice.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BillingRepositoryTest {
    @Autowired
    private BillingRepository billingRepository;
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
        appointmentRepository.save(appt);

        Billing bill = new Billing();
        bill.setAppointment(appt);
        bill.setAmount(BigDecimal.valueOf(500));
        bill.setStatus("PAID");
        Billing saved = billingRepository.save(bill);
        assertNotNull(saved.getId());
        Billing found = billingRepository.findByAppointmentId(appt.getId());
        assertNotNull(found);
    }
}
