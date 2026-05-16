package com.scalers.productserviceebatch.services;

import com.scalers.productserviceebatch.paymentgateway.PaymentGateway;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(String orderId, String phoneNumber) {
        return paymentGateway.initiatePayment(orderId, phoneNumber);
    }


}
