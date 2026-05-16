package com.scalers.productserviceebatch.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {

    private String orderId;
    private String phoneNumber;
}