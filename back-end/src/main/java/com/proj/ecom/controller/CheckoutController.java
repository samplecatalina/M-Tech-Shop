package com.proj.ecom.controller;

import com.proj.ecom.dto.PaymentInfo;
import com.proj.ecom.dto.Purchase;
import com.proj.ecom.dto.PurchaseResponse;
import com.proj.ecom.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

     private CheckoutService checkoutService;

     @Autowired
     public CheckoutController(CheckoutService checkoutService) {
         this.checkoutService = checkoutService;
     }

     @PostMapping("/purchase")
     public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
         PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
         return purchaseResponse;
     }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {

        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);

        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }
}
