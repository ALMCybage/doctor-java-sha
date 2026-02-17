package com.example.appointmentservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    @Test
    void testGettersAndSetters() {
        Doctor doc = new Doctor();
        doc.setId(1L);
        doc.setName("Dr. Test");
        doc.setSpecialization("Cardiology");
        doc.setEmail("test@example.com");
        doc.setPhone("1234567890");
        assertEquals(1L, doc.getId());
        assertEquals("Dr. Test", doc.getName());
        assertEquals("Cardiology", doc.getSpecialization());
        assertEquals("test@example.com", doc.getEmail());
        assertEquals("1234567890", doc.getPhone());
    }
}
