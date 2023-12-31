package com.proj.ecom.service;

import com.proj.ecom.dao.CustomerRepository;
import com.proj.ecom.dto.Purchase;
import com.proj.ecom.dto.PurchaseResponse;
import com.proj.ecom.entity.Customer;
import com.proj.ecom.entity.Order;
import com.proj.ecom.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add); // order::add is the same as order.add(orderItem)

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID (Universally Unique IDentifier) number (UUID version-4)
        // For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier#Version_4_(random)
        return java.util.UUID.randomUUID().toString();
    }
}
