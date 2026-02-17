package com.example.appointmentservice.controller;

import com.example.appointmentservice.model.Billing;
import com.example.appointmentservice.repository.BillingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/billings")
public class BillingController {
    private final BillingRepository billingRepository;

    public BillingController(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @GetMapping
    public List<Billing> getAllBillings() {
        return billingRepository.findAll();
    }

    @GetMapping("/{billingId}")
    public ResponseEntity<Billing> getBillingById(@PathVariable Long billingId) {
        Optional<Billing> billing = billingRepository.findById(billingId);
        return billing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Billing createBilling(@RequestBody Billing billing) {
        return billingRepository.save(billing);
    }

    @PutMapping("/{billingId}")
    public ResponseEntity<Billing> updateBilling(@PathVariable Long billingId, @RequestBody Billing billingDetails) {
        return billingRepository.findById(billingId).map(billing -> {
            billing.setAmount(billingDetails.getAmount());
            billing.setStatus(billingDetails.getStatus());
            billing.setAppointment(billingDetails.getAppointment());
            return ResponseEntity.ok(billingRepository.save(billing));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{billingId}")
    public ResponseEntity<Void> deleteBilling(@PathVariable Long billingId) {
        if (billingRepository.existsById(billingId)) {
            billingRepository.deleteById(billingId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/appointments/{appointmentId}")
    public ResponseEntity<Billing> getBillingByAppointment(@PathVariable Long appointmentId) {
        Billing billing = billingRepository.findByAppointmentId(appointmentId);
        if (billing != null) {
            return ResponseEntity.ok(billing);
        }
        return ResponseEntity.notFound().build();
    }
}
