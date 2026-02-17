package com.example.appointmentservice.controller;

import com.example.appointmentservice.model.Doctor;
import com.example.appointmentservice.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorRepository doctorRepository;

    @Test
    void testGetAllDoctors() throws Exception {
        Doctor doc = new Doctor();
        doc.setId(1L);
        doc.setName("Dr. Test");
        Mockito.when(doctorRepository.findAll()).thenReturn(Arrays.asList(doc));
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Dr. Test")));
    }

    @Test
    void testGetDoctorById() throws Exception {
        Doctor doc = new Doctor();
        doc.setId(1L);
        doc.setName("Dr. Test");
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doc));
        mockMvc.perform(get("/api/doctors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Dr. Test")));
    }

    @Test
    void testCreateDoctor() throws Exception {
        Doctor doc = new Doctor();
        doc.setId(1L);
        doc.setName("Dr. Test");
        Mockito.when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doc);
        mockMvc.perform(post("/api/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Dr. Test\",\"specialization\":\"Cardiology\",\"email\":\"test@example.com\",\"phone\":\"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Dr. Test")));
    }

    @Test
    void testUpdateDoctor() throws Exception {
        Doctor doc = new Doctor();
        doc.setId(1L);
        doc.setName("Dr. Test");
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doc));
        Mockito.when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(doc);
        mockMvc.perform(put("/api/doctors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Dr. Test\",\"specialization\":\"Cardiology\",\"email\":\"test@example.com\",\"phone\":\"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Dr. Test")));
    }

    @Test
    void testDeleteDoctor() throws Exception {
        Mockito.when(doctorRepository.existsById(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/doctors/1"))
                .andExpect(status().isNoContent());
    }
}
