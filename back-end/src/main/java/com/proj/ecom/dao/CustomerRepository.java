package com.proj.ecom.dao;

import com.proj.ecom.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Spring Data JPA will automatically generate the query for us
    // select * from customer c where c.email = theEmail;
    Customer findByEmail(String theEmail);
}
