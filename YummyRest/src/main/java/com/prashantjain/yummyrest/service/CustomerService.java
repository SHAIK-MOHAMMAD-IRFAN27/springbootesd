package com.prashantjain.yummyrest.service;

import com.prashantjain.yummyrest.dto.CustomerRequest;
import com.prashantjain.yummyrest.dto.LoginRequest;
import com.prashantjain.yummyrest.entity.Customer;
import com.prashantjain.yummyrest.mapper.CustomerMapper;
import com.prashantjain.yummyrest.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(@Valid CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }

    public String login(@Valid LoginRequest request) {
        Customer customer = mapper.loginEntity(request);
        Customer cust = repo.findByEmail(customer.getEmail());
        if(cust.getPassword().equals(customer.getPassword())) {
            return "Logged in";
        }
        return "Login Failed";
    }
}
