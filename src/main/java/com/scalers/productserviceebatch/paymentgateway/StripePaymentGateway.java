package com.scalers.productserviceebatch.paymentgateway;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.key}")
    private String apiKey;

    @Override
    public String initiatePayment(String orderId, String phoneNumber) {
        // Simulate payment processing with Stripe
        System.out.println("Initiating payment with Stripe for Order ID: " + orderId + " and Phone Number: " + phoneNumber);
        //1. Creating Price
        Stripe.apiKey = apiKey;
        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("EUR")
                        .setUnitAmount(1000L) // Amount in paise (e.g., 1000 paise = 10 INR)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("IPhone Charger").build()
                        )
                        .build();
        Price price=null;
        try {
            price = Price.create(params);
        }
        catch (StripeException e) {
            throw new RuntimeException(e);
        }


        PaymentLinkCreateParams linkParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        ).setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://scaler.com/")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
        PaymentLink paymentLink = null;
        try {
            paymentLink = PaymentLink.create(linkParams);
        }
        catch (StripeException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Stripe payment initiated successfully for Order ID: " + orderId + ". Payment Link: " + paymentLink.getUrl());

        return paymentLink.toString();

    }
}
