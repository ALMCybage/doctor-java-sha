package com.example.appointmentservice.controller;

import com.example.appointmentservice.model.Billing;
import com.example.appointmentservice.repository.BillingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(BillingController.class)
class BillingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillingRepository billingRepository;

    @Test
    void testGetAllBillings() throws Exception {
        Billing bill = new Billing();
        bill.setId(1L);
        bill.setAmount(BigDecimal.valueOf(500));
        Mockito.when(billingRepository.findAll()).thenReturn(Arrays.asList(bill));
        mockMvc.perform(get("/api/billings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amount", is(500)));
    }

    @Test
    void testGetBillingById() throws Exception {
        Billing bill = new Billing();
        bill.setId(1L);
        bill.setAmount(BigDecimal.valueOf(500));
        Mockito.when(billingRepository.findById(1L)).thenReturn(Optional.of(bill));
        mockMvc.perform(get("/api/billings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(500)));
    }

    @Test
    void testCreateBilling() throws Exception {
        Billing bill = new Billing();
        bill.setId(1L);
        bill.setAmount(BigDecimal.valueOf(500));
        Mockito.when(billingRepository.save(Mockito.any(Billing.class))).thenReturn(bill);
        mockMvc.perform(post("/api/billings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"appointment\":{\"id\":1},\"amount\":500,\"status\":\"PAID\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(500)));
    }

    @Test
    void testUpdateBilling() throws Exception {
        Billing bill = new Billing();
        bill.setId(1L);
        bill.setAmount(BigDecimal.valueOf(500));
        Mockito.when(billingRepository.findById(1L)).thenReturn(Optional.of(bill));
        Mockito.when(billingRepository.save(Mockito.any(Billing.class))).thenReturn(bill);
        mockMvc.perform(put("/api/billings/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"appointment\":{\"id\":1},\"amount\":500,\"status\":\"PAID\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(500)));
    }

    @Test
    void testDeleteBilling() throws Exception {
        Mockito.when(billingRepository.existsById(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/billings/1"))
                .andExpect(status().isNoContent());
    }
}
