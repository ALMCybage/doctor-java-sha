package com.example.appointmentservice.repository;

import com.example.appointmentservice.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {
    Billing findByAppointmentId(Long appointmentId);
}