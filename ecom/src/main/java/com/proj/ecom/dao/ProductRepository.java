package com.proj.ecom.dao;

import com.proj.ecom.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Behind the scenes, Spring Data REST will execute a query similar to this:
    // select * from product where category_id=?
    Page<Product> findByCategoryId(@Param("id") Long id, org.springframework.data.domain.Pageable pageable);
}
