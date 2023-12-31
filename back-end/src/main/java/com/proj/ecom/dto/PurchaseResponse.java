package com.proj.ecom.dto;

import lombok.Data;

@Data
public class PurchaseResponse {

    // Lombok @Data generates constructor only for final fields
    // Another option is to use @NonNull annotation on the fields instead of final
    // @NonNull
    // private String orderTrackingNumber;
    private final String orderTrackingNumber;
}
