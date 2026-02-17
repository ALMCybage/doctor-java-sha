package com.example.appointmentservice.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BillingTest {
    @Test
    void testGettersAndSetters() {
        Billing bill = new Billing();
        bill.setId(1L);
        bill.setAppointment(new Appointment());
        bill.setAmount(BigDecimal.valueOf(500));
        bill.setStatus("PAID");
        assertEquals(1L, bill.getId());
        assertNotNull(bill.getAppointment());
        assertEquals(BigDecimal.valueOf(500), bill.getAmount());
        assertEquals("PAID", bill.getStatus());
    }
}
