package com.prashantjain.yummyrest.service;

import com.prashantjain.yummyrest.dto.CustomerRequest;
import com.prashantjain.yummyrest.dto.CustomerResponse;
import com.prashantjain.yummyrest.dto.LoginRequest;
import com.prashantjain.yummyrest.entity.Customer;
import com.prashantjain.yummyrest.helper.EncryptionService;
import com.prashantjain.yummyrest.helper.JWTHelper;
import com.prashantjain.yummyrest.mapper.CustomerMapper;
import com.prashantjain.yummyrest.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        repo.save(customer);
        return "Created";
    }

    public String login(@Valid LoginRequest request) {
        Customer customer = mapper.loginEntity(request);
        String email = customer.getEmail();
        String password = customer.getPassword();
        Customer cust = repo.findByEmail(email);
//        if (password.equals(cust.getPassword())) {
//            return "Logged in";
//        }
//        else {
//            return "Wrong password";
//        }
        if(!encryptionService.validates(password, cust.getPassword())) {
            return "Wrong Password or Email";
        }
        return jwtHelper.generateToken(request.email());

    }
}
