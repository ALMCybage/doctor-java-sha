package com.example.appointmentservice.repository;

import com.example.appointmentservice.model.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void testSaveAndFind() {
        Doctor doc = new Doctor();
        doc.setName("Dr. Repo");
        doc.setSpecialization("Test");
        doc.setEmail("repo@example.com");
        doc.setPhone("12345");
        Doctor saved = doctorRepository.save(doc);
        assertNotNull(saved.getId());
        assertEquals("Dr. Repo", saved.getName());
        assertTrue(doctorRepository.findById(saved.getId()).isPresent());
    }
}
