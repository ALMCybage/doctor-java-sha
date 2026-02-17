package com.example.appointmentservice.controller;

import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.model.Doctor;
import com.example.appointmentservice.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentRepository appointmentRepository;
    @MockBean
    private com.example.appointmentservice.repository.PatientRepository patientRepository;

    @Test
    void testGetAllAppointments() throws Exception {
        Appointment appt = new Appointment();
        appt.setId(1L);
        appt.setStatus("BOOKED");
        Mockito.when(appointmentRepository.findAll()).thenReturn(Arrays.asList(appt));
        mockMvc.perform(get("/api/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is("BOOKED")));
    }

    @Test
    void testGetAppointmentById() throws Exception {
        Appointment appt = new Appointment();
        appt.setId(1L);
        appt.setStatus("BOOKED");
        Mockito.when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appt));
        mockMvc.perform(get("/api/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("BOOKED")));
    }

    @Test
    void testCreateAppointment() throws Exception {
        Appointment appt = new Appointment();
        appt.setId(1L);
        appt.setStatus("BOOKED");
        Mockito.when(appointmentRepository.save(Mockito.any(Appointment.class))).thenReturn(appt);
        Mockito.when(patientRepository.findById(101L)).thenReturn(Optional.of(new com.example.appointmentservice.model.Patient()));
        mockMvc.perform(post("/api/appointments")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"doctor\":{\"id\":1},\"patient\":{\"id\":101},\"appointmentTime\":\"2026-02-15T10:00:00\",\"status\":\"BOOKED\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is("BOOKED")));
    }

    @Test
    void testUpdateAppointment() throws Exception {
        Appointment appt = new Appointment();
        appt.setId(1L);
        appt.setStatus("BOOKED");
        Mockito.when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appt));
        Mockito.when(appointmentRepository.save(Mockito.any(Appointment.class))).thenReturn(appt);
        Mockito.when(patientRepository.findById(101L)).thenReturn(Optional.of(new com.example.appointmentservice.model.Patient()));
        mockMvc.perform(put("/api/appointments/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":1,\"doctor\":{\"id\":1},\"patient\":{\"id\":101},\"appointmentTime\":\"2026-02-15T10:00:00\",\"status\":\"BOOKED\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is("BOOKED")));
    }

    @Test
    void testDeleteAppointment() throws Exception {
        Mockito.when(appointmentRepository.existsById(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/appointments/1"))
                .andExpect(status().isNoContent());
    }
}
