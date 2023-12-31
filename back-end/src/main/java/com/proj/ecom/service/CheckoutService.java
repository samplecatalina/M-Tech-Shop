package com.proj.ecom.service;

import com.proj.ecom.dto.Purchase;
import com.proj.ecom.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
