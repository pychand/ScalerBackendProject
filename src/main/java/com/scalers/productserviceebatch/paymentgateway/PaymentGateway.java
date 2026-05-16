package com.scalers.productserviceebatch.paymentgateway;

public interface PaymentGateway {

    String initiatePayment(String orderId, String phoneNumber);
}
