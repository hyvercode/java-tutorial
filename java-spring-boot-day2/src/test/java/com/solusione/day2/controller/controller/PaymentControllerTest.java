package com.solusione.day2.controller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solusione.day2.controller.PaymentController;
import com.solusione.day2.model.request.payment.PaymentRequest;
import com.solusione.day2.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {
    @InjectMocks
    private PaymentController paymentController;
    @Mock
    private PaymentService paymentService;

    private MockMvc mockMvc;

    private PaymentRequest paymentRequest;

    @BeforeEach
    public void setup() {
        paymentController = new PaymentController(paymentService);
        mockMvc = standaloneSetup(paymentController).build();
        paymentRequest =PaymentRequest.builder()
                .orderId("1")
                .amount(new BigDecimal("100000"))
                .build();
    }

    @Test
    void postPaymentTest() throws Exception {
        mockMvc.perform(post("/api/v1/payments")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(paymentRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

}
