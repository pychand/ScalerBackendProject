package com.scalers.productserviceebatch.controllers;




import com.scalers.productserviceebatch.dtos.PaymentRequestDTO;
import com.scalers.productserviceebatch.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> createPaymentLink(@RequestBody PaymentRequestDTO paymentRequestDTO) throws StripeException {
        String paymentLink = paymentService.initiatePayment(paymentRequestDTO.getOrderId(), paymentRequestDTO.getPhoneNumber());
        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
    }

    @PostMapping("/webhook")
    public void handleWebhook() {
        System.out.println("Webhook received here");
    }
}
