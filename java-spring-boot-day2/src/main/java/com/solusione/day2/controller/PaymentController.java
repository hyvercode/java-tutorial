package com.solusione.day2.controller;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.solusione.day2.model.request.payment.PaymentRequest;
import com.solusione.day2.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse postPayment(@RequestBody PaymentRequest paymentRequest){
        return paymentService.execute(paymentRequest);
    }
}
