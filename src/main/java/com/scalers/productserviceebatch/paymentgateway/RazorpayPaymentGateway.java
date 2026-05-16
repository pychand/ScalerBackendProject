package com.scalers.productserviceebatch.paymentgateway;

import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentGateway {
    public String initiatePayment(String orderId, String phoneNumber) {
        // Simulate payment initiation logic for Razorpay
        // In a real implementation, you would integrate with Razorpay's API here
        return "Razorpay payment initiated for order " + orderId + " and phone number " + phoneNumber;
    }
}
