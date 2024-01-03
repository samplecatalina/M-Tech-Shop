package com.proj.ecom.service;

import com.proj.ecom.dto.PaymentInfo;
import com.proj.ecom.dto.Purchase;
import com.proj.ecom.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
