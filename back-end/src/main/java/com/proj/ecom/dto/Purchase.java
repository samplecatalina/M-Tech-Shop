package com.proj.ecom.dto;

import com.proj.ecom.entity.Address;
import com.proj.ecom.entity.Customer;
import com.proj.ecom.entity.Order;
import com.proj.ecom.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
