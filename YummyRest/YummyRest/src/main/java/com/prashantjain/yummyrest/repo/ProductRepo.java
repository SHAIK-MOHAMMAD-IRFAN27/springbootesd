package com.prashantjain.yummyrest.repo;

import com.prashantjain.yummyrest.entity.Customer;
import com.prashantjain.yummyrest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.price between 15 and 30 order by p.price desc limit 2")
    List<Product> products15To30();
}

